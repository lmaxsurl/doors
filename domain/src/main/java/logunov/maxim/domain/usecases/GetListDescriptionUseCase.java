package logunov.maxim.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.Description;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class GetListDescriptionUseCase extends BaseUseCase {

    private DoorRepository doorRepository;

    @Inject
    public GetListDescriptionUseCase(PostExecutionThread postExecutionThread,
                                     DoorRepository doorRepository) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    public Observable<List<Description>> getDescriptions(){
        return doorRepository
                .getDescriptions()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
