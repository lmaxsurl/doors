package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Type;

public interface DoorRepository {

    Observable<List<Door>> getAll(String doorClass, String doorType);

    Observable<Door> getDoor(String doorClass, String id);

    Observable<List<Type>> getDoorTypes(String doorClass);

}
