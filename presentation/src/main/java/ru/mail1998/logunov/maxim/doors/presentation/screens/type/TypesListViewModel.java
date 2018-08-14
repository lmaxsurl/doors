package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.usecases.GetListTypeUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;

public class TypesListViewModel extends BaseViewModel<TypesListRouter> {

    public TypeItemAdapter adapter = new TypeItemAdapter();
    private String doorClass;

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
        adapter.observeItemClick()
                .subscribe(new Observer<ClickedItemModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(ClickedItemModel clickedItemModel) {
                        if (clickedItemModel.getEntity() instanceof Type) {
                            router.goToDoorList(doorClass,
                                    ((Type) clickedItemModel.getEntity()).getType());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.set(router.getErrorMessage(e));
                        isConnected.set(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // method that load data fom server
    private void getData() {
        getListTypeUseCase
                .getTypes(doorClass)
                .subscribe(new Observer<List<Type>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Type> types) {
                        adapter.setItems(types);
                        dismissProgressBar();
                        isConnected.set(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.set(router.getErrorMessage(e));
                        isConnected.set(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // set door class for uploading data
    public void setDoorClass(String doorClass) {
        this.doorClass = doorClass;
        getData();
    }

    // method that called when button 'try again' clicked
    public void tryAgainClick() {
        getData();
    }
}
