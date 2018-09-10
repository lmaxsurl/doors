package logunov.maxim.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import logunov.maxim.data.entity.DescriptionResponse;
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.data.entity.TypeResponse;
import logunov.maxim.data.network.RestService;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.repositories.DoorRepository;

public class DoorRepositoryImpl implements DoorRepository {

    private RestService restService;

    @Inject
    public DoorRepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Observable<List<Door>> getDoors(final String doorClass, String doorType) {
        return restService
                .getAllDoors(doorClass, doorType)
                .flatMap(new Function<List<DoorResponse>, ObservableSource<List<Door>>>() {
                    @Override
                    public ObservableSource<List<Door>> apply(final List<DoorResponse> doorResponses) {
                        return restService
                                .getDescriptions()
                                .map(new Function<List<DescriptionResponse>, List<Door>>() {
                                    @Override
                                    public List<Door> apply(List<DescriptionResponse> descriptionResponses) {
                                        List<Door> doors = new ArrayList<>();
                                        for (DoorResponse doorResponse : doorResponses) {
                                            doors.add(mapDoor(doorResponse, descriptionResponses));
                                        }
                                        return doors;
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<List<Type>> getDoorTypes(String doorClass) {
        return restService
                .getTypes(doorClass)
                .map(new Function<List<TypeResponse>, List<Type>>() {
                    @Override
                    public List<Type> apply(List<TypeResponse> typeResponses) {
                        final List<Type> list = new ArrayList<>();
                        for (TypeResponse typeResponse : typeResponses) {
                            list.add(mapType(typeResponse));
                        }
                        return list;
                    }
                });
    }

    // transform DoorResponse to Door with description
    private Door mapDoor(DoorResponse doorResponse, List<DescriptionResponse> descriptionResponses) {
        return new Door(doorResponse.getTitle(),
                descriptionResponses.get(doorResponse.getDescription_id() - 1).getDescription(),
                doorResponse.getDoorUrl(),
                doorResponse.getHighQualityDoorUrl());
    }

    // transform TypeResponse to Type
    private Type mapType(TypeResponse typeResponse) {
        return new Type(typeResponse.getType());
    }

}
