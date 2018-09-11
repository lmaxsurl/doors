package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.usecases.GetListTypeUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;

public class TypesListViewModel extends BaseViewModel<TypesListRouter> {

    public TypeItemAdapter adapter = new TypeItemAdapter();
    private String doorClass;

    private Consumer<List<Type>> doOnNext = new Consumer<List<Type>>() {
        @Override
        public void accept(List<Type> types) {
            adapter.setItems(types);
            dismissProgressBar();
            isConnected.set(true);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            if (clickedItemModel.getEntity() instanceof Type) {
                router.goToDoorList(doorClass,
                        ((Type) clickedItemModel.getEntity()).getType());
            }
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
                        .getTypes(doorClass)
                        .subscribe(doOnNext, doOnError));
    }

    // set door class for uploading data
    public void setDoorClass(String doorClass) {
        this.doorClass = doorClass;
        noParams = false;
        getData();
    }

    // method that called when button 'try again' was clicked
    public void tryAgainClick() {
        getData();
    }
}
