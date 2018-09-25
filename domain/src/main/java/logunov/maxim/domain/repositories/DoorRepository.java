package logunov.maxim.domain.repositories;

import java.util.List;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Type;

public interface DoorRepository {

    Observable<List<Door>> getDoors(String doorClass, int typeId, int offset, int pageSize);

    Observable<List<Type>> getTypes(String doorType, int offset, int pageSize);

}
