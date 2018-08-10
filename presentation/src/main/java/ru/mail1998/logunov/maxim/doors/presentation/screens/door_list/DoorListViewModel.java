package ru.mail1998.logunov.maxim.doors.presentation.screens.door_list;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.usecases.GetListDoorUserCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;

public class DoorListViewModel extends BaseViewModel<DoorListRouter> {

    private String doorsClass;
    private String doorsType;
    public DoorItemAdapter adapter = new DoorItemAdapter();

    @Inject
    public GetListDoorUserCase getListDoorUserCase;

    public DoorListViewModel() {
        showProgressBar();
    }

    public void setDoorsParams(String doorsClass, String doorsType){
        this.doorsClass = doorsClass;
        this.doorsType = doorsType;
        getData();
    }

    private void getData() {
        if(isConnected.get())
        getListDoorUserCase
                .getDoors(doorsClass, doorsType)
                .subscribe(new Observer<List<Door>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Door> doors) {
                        adapter.setItems(doors);
                        dismissProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                        router.finishActivity();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void tryAgain(){
        isConnected.set(router.checkInternetAccess());
        getData();

    }

}
