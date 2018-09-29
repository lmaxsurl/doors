package logunov.maxim.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class FindDoorsUseCase extends BaseUseCase {

    private DoorRepository doorRepository;

    @Inject
    public FindDoorsUseCase(PostExecutionThread postExecutionThread, DoorRepository doorRepository) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    public Observable<List<Door>> findDoors(String doorClass, String request, int offset, int pageSize){
        return doorRepository
                .findDoors(doorClass, request, offset, pageSize)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
