package logunov.maxim.domain.usecases;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class GetDoorUseCase extends BaseUseCase {

    private DoorRepository doorRepository;

    public GetDoorUseCase(PostExecutionThread postExecutionThread, DoorRepository doorRepository) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    public Observable<Door> getDoor(String doorClass, String id){
        return doorRepository
                .getDoor(doorClass, id)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
