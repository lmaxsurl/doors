package logunov.maxim.data.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import logunov.maxim.data.database.AppDatabase;
import logunov.maxim.data.entity.DescriptionResponse;
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.data.entity.TypeResponse;
import logunov.maxim.data.network.RestService;
import logunov.maxim.domain.entity.Description;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Error;
import logunov.maxim.domain.entity.ErrorType;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.repositories.DoorRepository;

public class DoorRepositoryImpl implements DoorRepository {

    private RestService restService;
    private AppDatabase appDatabase;
    private long lastDescriptionUpdateTime;
    private static final long HALF_AN_HOUR_IN_MILLIS = 1800000L;

    @Inject
    public DoorRepositoryImpl(RestService restService, Context context) {
        this.restService = restService;
        this.appDatabase = AppDatabase.getInstance(context);
    }

    @Override
    public Observable<List<Door>> getAll(String doorClass, String doorType) {
        return restService
                .getAllDoors(doorClass, doorType)
                .map(new Function<List<DoorResponse>, List<Door>>() {
                    @Override
                    public List<Door> apply(List<DoorResponse> doorResponses) throws Exception {
                        final List<Door> list = new ArrayList<>();
                        for (DoorResponse doorResponse : doorResponses) {
                            list.add(mapDoor(doorResponse));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Observable<Door> getDoor(String doorClass, String id) {
        return restService
                .getDoor(doorClass, id)
                .map(new Function<DoorResponse, Door>() {
                    @Override
                    public Door apply(DoorResponse doorResponse) throws Exception {
                        return mapDoor(doorResponse);
                    }
                });
    }

    @Override
    public Observable<List<Description>> getDescriptions() {

        return appDatabase
                .getUserDao()
                .getAll()
                .take(1)
                .toObservable()
                .flatMap(new Function<List<DescriptionResponse>, ObservableSource<List<DescriptionResponse>>>() {
                    @Override
                    public ObservableSource<List<DescriptionResponse>> apply(final List<DescriptionResponse> descriptionResponses) throws Exception {
                        if (descriptionResponses.isEmpty()
                                || lastDescriptionUpdateTime + HALF_AN_HOUR_IN_MILLIS < System.currentTimeMillis()) {
                            return restService
                                    .getDescriptions()
                                    .doOnNext(new Consumer<List<DescriptionResponse>>() {
                                        @Override
                                        public void accept(List<DescriptionResponse> descriptionResponses) throws Exception {
                                            lastDescriptionUpdateTime = System.currentTimeMillis();
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<DescriptionResponse>>>() {
                                        @Override
                                        public ObservableSource<? extends List<DescriptionResponse>> apply(Throwable throwable) throws Exception {
                                            if (throwable instanceof Error) {
                                                Error error = (Error) throwable;

                                                if (error.getType() == ErrorType.INTERNET_IS_NOT_AVAILABLE
                                                        && !descriptionResponses.isEmpty()) {
                                                    return Observable.just(descriptionResponses);
                                                }
                                            }
                                            return Observable.error(throwable);
                                        }
                                    });
                        } else {
                            return Observable.just(descriptionResponses);
                        }
                    }
                })
                .map(new Function<List<DescriptionResponse>, List<Description>>() {
                    @Override
                    public List<Description> apply
                            (List<DescriptionResponse> descriptionResponses) throws Exception {
                        final List<Description> list = new ArrayList<>();
                        for (DescriptionResponse descriptionResponse : descriptionResponses) {
                            list.add(mapDescription(descriptionResponse));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Observable<Description> getDescription(final int id) {
        return appDatabase
                .getUserDao()
                .getDescription(id)
                .take(1)
                .toObservable()
                .flatMap(new Function<DescriptionResponse, ObservableSource<DescriptionResponse>>() {
                    @Override
                    public ObservableSource<DescriptionResponse> apply(final DescriptionResponse descriptionResponse) throws Exception {
                        if (descriptionResponse == null
                                || lastDescriptionUpdateTime + HALF_AN_HOUR_IN_MILLIS < System.currentTimeMillis()) {
                            return restService
                                    .getDescription(id)
                                    .doOnNext(new Consumer<DescriptionResponse>() {
                                        @Override
                                        public void accept(DescriptionResponse descriptionResponses) throws Exception {
                                            lastDescriptionUpdateTime = System.currentTimeMillis();
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends DescriptionResponse>>() {
                                        @Override
                                        public ObservableSource<? extends DescriptionResponse> apply(Throwable throwable) throws Exception {
                                            if (throwable instanceof Error) {
                                                Error error = (Error) throwable;

                                                if (error.getType() == ErrorType.INTERNET_IS_NOT_AVAILABLE
                                                        && descriptionResponse != null) {
                                                    return Observable.just(descriptionResponse);
                                                }
                                            }
                                            return Observable.error(throwable);
                                        }
                                    });
                        } else {
                            return Observable.just(descriptionResponse);
                        }
                    }
                })
                .map(new Function<DescriptionResponse, Description>() {
                    @Override
                    public Description apply(DescriptionResponse descriptionResponse) throws Exception {
                        return mapDescription(descriptionResponse);
                    }
                });
    }

    @Override
    public Observable<List<Type>> getDoorTypes(String doorClass) {
        return restService
                .getTypes(doorClass)
                .map(new Function<List<TypeResponse>, List<Type>>() {
                    @Override
                    public List<Type> apply(List<TypeResponse> typeResponses) throws Exception {
                        final List<Type> list = new ArrayList<>();
                        for (TypeResponse typeResponse : typeResponses) {
                            Type type = mapType(typeResponse);
                            if (!list.contains(type))
                                list.add(type);
                        }
                        return list;
                    }
                });
    }

    private Door mapDoor(DoorResponse doorResponse) {
        return new Door(doorResponse.getTitle(),
                doorResponse.getDescriptionId(),
                doorResponse.getDoorUrl(),
                doorResponse.getGlazedDoorUrl());
    }

    private Type mapType(TypeResponse typeResponse) {
        return new Type(typeResponse.getType());
    }

    private Description mapDescription(DescriptionResponse descriptionResponse) {
        return new Description(descriptionResponse.getId(), descriptionResponse.getDescription());
    }


}
