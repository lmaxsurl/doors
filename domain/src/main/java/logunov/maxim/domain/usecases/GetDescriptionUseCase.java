package logunov.maxim.domain.usecases;


import io.reactivex.Observable;
import logunov.maxim.domain.entity.Description;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;

public class GetDescriptionUseCase extends BaseUseCase {

    private DoorRepository doorRepository;

    public GetDescriptionUseCase(PostExecutionThread postExecutionThread, DoorRepository doorRepository) {
        super(postExecutionThread);
        this.doorRepository = doorRepository;
    }

    public Observable<Description> getDescription(int id){
        return doorRepository
                .getDescription(id)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
