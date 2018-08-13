package ru.mail1998.logunov.maxim.doors.presentation.screens.door_list;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.usecases.GetListDoorUserCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;
import ru.mail1998.logunov.maxim.doors.presentation.utils.Extras;

public class DoorListViewModel extends BaseViewModel<DoorListRouter> {

    private String doorsClass;
    private String doorsType;
    private final String NULL_URL = "null";
    public DoorItemAdapter adapter = new DoorItemAdapter();
    public ObservableBoolean showDoor = new ObservableBoolean(false);
    public ObservableField<String> doorUrl = new ObservableField<>(NULL_URL);

    @Inject
    public GetListDoorUserCase getListDoorUserCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public DoorListViewModel() {
        showProgressBar();
    }

    private void showImage(Door door) {
        doorUrl.set(door.getHighQualityDoorUrl());
        showDoor.set(true);
    }

    public void setDoorsParams(String doorsClass, String doorsType) {
        this.doorsClass = doorsClass;
        this.doorsType = doorsType;
        isConnected.set(router.checkInternetAccess());
        if (doorsClass.equals(Extras.METAL_DOOR_CLASS))
            adapter.observeItemClick()
                    .subscribe(new Observer<ClickedItemModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(ClickedItemModel clickedItemModel) {
                            showImage((Door) clickedItemModel.getEntity());
                        }

                        @Override
                        public void onError(Throwable e) {
                            showErrorMessage(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        getData();
    }

    private void getData() {
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
                        showErrorMessage(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onTryAgainClick() {
        isConnected.set(router.checkInternetAccess());
        getData();
    }

    public void hideImage() {
        showDoor.set(false);
        doorUrl.set(NULL_URL);
    }

    private void showErrorMessage(Throwable throwable) {
        errorMessage.set(router.getErrorMessage(throwable));
        isConnected.set(false);
    }

}
