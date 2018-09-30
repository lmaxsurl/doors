package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.usecases.GetListTypeUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;

public class TypesListViewModel extends BaseViewModel<TypesListRouter> {

    public TypeItemAdapter adapter = new TypeItemAdapter();
    private int offset = 0;
    public final int PAGE_SIZE = 5;
    private String doorClass;
    private String doorTypes;

    private Consumer<List<Type>> doOnNext = new Consumer<List<Type>>() {
        @Override
        public void accept(List<Type> types) {
            adapter.addItems(types);
            hideProgressBar();
            isConnected.set(true);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            if (clickedItemModel.getEntity() instanceof Type) {
                router.goToDoorList(doorClass,
                        ((Type) clickedItemModel.getEntity()).getId());
            }
        }
    };

    private Consumer<Integer> doOnScroll = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            offset = integer;
            getData();
        }
    };

    @Inject
    public GetListTypeUseCase getListTypeUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public TypesListViewModel() {
        // show progress bar until data will load
        showProgressBar();
        // click processing on item in recycler view
        getCompositeDisposable().add(
                adapter.observeItemClick()
                        .subscribe(doOnClick, doOnError));

    }

    // method that load data from server
    private void getData() {
        getCompositeDisposable().add(
                getListTypeUseCase
                        .getTypes(doorTypes, offset, PAGE_SIZE)
                        .subscribe(doOnNext, doOnError));
    }

    public void subscribeScrolledItems(Observable<Integer> observable) {
        getCompositeDisposable().add(
                observable
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) {
                                return offset < integer;
                            }
                        })
                        .subscribe(doOnScroll, doOnError));
    }

    // method that called when button 'try again' was clicked
    public void tryAgainClick() {
        getData();
    }

    public void setParams(String doorClass, String doorTypes){
        this.doorClass = doorClass;
        this.doorTypes = doorTypes;
        getData();
    }
}
