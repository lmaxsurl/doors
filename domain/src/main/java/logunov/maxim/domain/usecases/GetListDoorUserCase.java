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

    public Observable<List<Door>> getDoors(String doorClass, String doorType) {
        return doorRepository
                .getAll(doorClass, doorType)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
