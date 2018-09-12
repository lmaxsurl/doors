package ru.mail1998.logunov.maxim.doors.presentation.screens.door;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.usecases.GetListDoorUserCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.custom.recycler.OffsetAndLimit;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_CLASS;

public class DoorListViewModel extends BaseViewModel<DoorListRouter> {

    private String doorsClass;
    private int typeId;
    private int offset = 0;
    public final int PAGE_SIZE = 5;
    private final String NULL_URL = "null";
    public DoorItemAdapter adapter = new DoorItemAdapter();
    public ObservableBoolean showDoor = new ObservableBoolean(false);
    public ObservableField<String> doorUrl = new ObservableField<>(NULL_URL);

    private Consumer<List<Door>> doOnNext = new Consumer<List<Door>>() {
        @Override
        public void accept(List<Door> doors) {
            adapter.addItems(doors);
            dismissProgressBar();
            isConnected.set(true);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            showImage((Door) clickedItemModel.getEntity());
        }
    };

    private Consumer<OffsetAndLimit> doOnScroll = new Consumer<OffsetAndLimit>() {
        @Override
        public void accept(OffsetAndLimit offsetAndLimit) {
            offset = offsetAndLimit.getOffset();
            getData();
        }
    };

    @Inject
    public GetListDoorUserCase getListDoorUserCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public DoorListViewModel() {
        showProgressBar();
    }

    //method that show high quality image of metal door
    private void showImage(Door door) {
        doorUrl.set(door.getHighQualityDoorUrl());
        showDoor.set(true);
    }

    //set params for uploading data
    public void setDataParams(String doorsClass, int typeId) {
        this.doorsClass = doorsClass;
        this.typeId = typeId;

        noParams = false;

        //this code below working only with metal doors
        if (doorsClass.equals(METAL_DOOR_CLASS))
            getCompositeDisposable().add(
                    adapter.observeItemClick()
                            .subscribe(doOnClick, doOnError));
        else
            adapter.setItemClickedEnabled(false);
        getData();
    }

    //method that upload data
    private void getData() {
        getCompositeDisposable().add(
                getListDoorUserCase
                        .getDoors(doorsClass, typeId, offset, PAGE_SIZE)
                        .subscribe(doOnNext, doOnError));
    }

    public void subscribeScrolledItems(Observable<OffsetAndLimit> observable) {
        getCompositeDisposable().add(
                observable
                        .filter(new Predicate<OffsetAndLimit>() {
                            @Override
                            public boolean test(OffsetAndLimit offsetAndLimit) {
                                return offset < offsetAndLimit.getOffset();
                            }
                        })
                        .subscribe(doOnScroll, doOnError));
    }

    public void onTryAgainClick() {
        getData();
    }

    // hide high quality image after click
    public void hideImage() {
        showDoor.set(false);
        doorUrl.set(NULL_URL);
    }

}
