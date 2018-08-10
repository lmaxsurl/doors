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
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.data.entity.TypeResponse;
import logunov.maxim.data.network.RestService;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Error;
import logunov.maxim.domain.entity.ErrorType;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.repositories.DoorRepository;

public class DoorRepositoryImpl implements DoorRepository {

    private RestService restService;

    @Inject
    public DoorRepositoryImpl(RestService restService) {
        this.restService = restService;
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
                doorResponse.getDescription(),
                doorResponse.getDoorUrl());
    }

    private Type mapType(TypeResponse typeResponse) {
        return new Type(typeResponse.getType());
    }

}
