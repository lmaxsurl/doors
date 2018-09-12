package maxim.logunov.domain.usecases;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.repositories.DoorRepository;
import logunov.maxim.domain.usecases.GetListTypeUseCase;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetListTypeUseCaseTest {

    @Mock
    public DoorRepository doorRepository;

    public GetListTypeUseCase listTypeUseCase;

    public TestScheduler testScheduler = new TestScheduler();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        listTypeUseCase = new GetListTypeUseCase(doorRepository, testScheduler, testScheduler);
    }

    @Test
    public void getTypesTest(){
        String doorClass = "wood_doors";
        List<Type> types = new ArrayList<>();
        types.add(new Type("a", id));
        types.add(new Type("b", id));
        types.add(new Type("c", id));
        types.add(new Type("b", id));
        types.add(new Type("b", id));
        types.add(new Type("c", id));
        types.add(new Type("c", id));
        types.add(new Type("b", id));
        types.add(new Type("a", id));

        final List<Type> currentList = new ArrayList<>();
        currentList.add(new Type("a", id));
        currentList.add(new Type("b", id));
        currentList.add(new Type("c", id));

        when(doorRepository.getDoorTypes(doorClass)).thenReturn(Observable.just(types));

        TestObserver<List<Type>> testObserver = TestObserver.create();

        listTypeUseCase
                .getTypes(doorClass)
                .subscribe(testObserver);
        testScheduler.triggerActions();

        testObserver.assertValue(new Predicate<List<Type>>() {
            @Override
            public boolean test(List<Type> types) {
                return types.size() == 3 && types.containsAll(currentList);
            }
        });
    }
}
