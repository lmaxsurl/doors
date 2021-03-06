package logunov.maxim.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
    private Observable<List<DescriptionResponse>> descriptions;
    private Function<List<DoorResponse>, ObservableSource<List<Door>>> doorFlatMap =
            new Function<List<DoorResponse>, ObservableSource<List<Door>>>() {
                @Override
                public ObservableSource<List<Door>> apply(final List<DoorResponse> doorResponses) {
                    return descriptions
                            .map(new Function<List<DescriptionResponse>, List<Door>>() {
                                @Override
                                public List<Door> apply(List<DescriptionResponse> descriptionResponses) {
                                    List<Door> doors = new ArrayList<>();
                                    for (DoorResponse doorResponse : doorResponses) {
                                        doors.add(mapDoor(doorResponse,
                                                descriptionResponses
                                                        .get(doorResponse.getDescriptionId() - 1)
                                                        .getDescription()));
                                    }
                                    return doors;
                                }
                            });
                }
            };

    @Inject
    public DoorRepositoryImpl(RestService restService) {
        this.restService = restService;
        descriptions = this.restService
                .getDescriptions()
                .cache();
    }

    @Override
    public Observable<List<Door>> getDoors(final String doorClass, int typeId, int offset, int pageSize) {
        return restService
                .getDoors(doorClass, typeId, offset, pageSize)
                .flatMap(doorFlatMap);
    }

    @Override
    public Observable<List<Type>> getTypes(String doorType, int offset, int pageSize) {
        return restService
                .getTypes(doorType, offset, pageSize)
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

    @Override
    public Observable<List<Door>> findDoors(String doorClass, String request, int offset, int pageSize) {
        return restService
                .findDoors(doorClass, request, offset, pageSize)
                .flatMap(doorFlatMap);
    }

    // transform DoorResponse to Door with description
    private Door mapDoor(DoorResponse doorResponse, String description) {
        return new Door(doorResponse.getTitle(),
                description,
                doorResponse.getDoorUrl(),
                doorResponse.getHighQualityDoorUrl());
    }

    // transform TypeResponse to Type
    private Type mapType(TypeResponse typeResponse) {
        return new Type(typeResponse.getType(), typeResponse.getId());
    }

}
