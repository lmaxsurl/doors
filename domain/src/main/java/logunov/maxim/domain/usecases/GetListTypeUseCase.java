package logunov.maxim.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class GetListTypeUseCase extends BaseUseCase {

    private DoorRepository doorRepository;

    @Inject
    public GetListTypeUseCase(PostExecutionThread postExecutionThread,
                              DoorRepository doorRepository) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    //return result of server request
    public Observable<List<Type>> getTypes(String doorClass) {
        return doorRepository
                .getDoorTypes(doorClass)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
