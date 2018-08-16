package logunov.maxim.domain.usecases;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.TestScheduler;
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

    //Test constructor
    public GetListTypeUseCase(DoorRepository doorRepository, Scheduler testScheduler, Scheduler testScheduler1) {
        super(testScheduler, testScheduler1);
        this.doorRepository = doorRepository;
    }

    //return result of server request
    public Observable<List<Type>> getTypes(String doorClass) {
        return doorRepository
                .getDoorTypes(doorClass)
                .filter(new Predicate<List<Type>>() {
                    @Override
                    public boolean test(List<Type> types) {
                        List<Type> list = new ArrayList<>();
                        for(Type type : types){
                            //check if list already contains this type
                            if (!list.contains(type))
                                list.add(type);
                        }
                        types.clear();
                        types.addAll(list);
                        return true;
                    }
                })
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
