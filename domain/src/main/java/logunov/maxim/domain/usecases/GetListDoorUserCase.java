package logunov.maxim.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class GetListDoorUserCase extends BaseUseCase {

    private DoorRepository doorRepository;

    @Inject
    public GetListDoorUserCase(DoorRepository doorRepository,
                               PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    //return result of server request
    public Observable<List<Door>> getDoors(String doorClass, int typeId, int offset, int pageSize) {
        return doorRepository
                .getDoors(doorClass, typeId, offset, pageSize)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
