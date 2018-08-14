package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Type;

public interface DoorRepository {

    Observable<List<Door>> getDoors(String doorClass, String doorType);

    Observable<List<Type>> getDoorTypes(String doorClass);

}
