package ru.mail1998.logunov.maxim.doors.presentation.screens.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.usecases.FindDoorsUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.custom.recycler.OffsetAndLimit;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorItemAdapter;

public class SearchDoorsViewModel extends BaseViewModel<SearchDoorsRouter> {

    private String doorsClass;
    private int offset = 0;
    public final int PAGE_SIZE = 10;
    public DoorItemAdapter adapter = new DoorItemAdapter();
    public ObservableBoolean showDoor = new ObservableBoolean(false);
    public ObservableField<String> doorUrl = new ObservableField<>("n");
    public ObservableField<String> request = new ObservableField<>("");

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
            findDoors();
        }
    };

    @Inject
    public FindDoorsUseCase findDoorsUseCase;

    //set params for uploading data
    public void setDataParams(String doorsClass) {
        this.doorsClass = doorsClass;

        getCompositeDisposable().add(
                adapter.observeItemClick()
                        .subscribe(doOnClick, doOnError));
    }

    public void afterTextChanged(Editable editable) {
        findDoors();
    }

    //method that upload data
    private void findDoors() {
        if (request.get().length() > 0)
            getCompositeDisposable().add(
                    findDoorsUseCase
                            .findDoors(doorsClass, request.get(), offset, PAGE_SIZE)
                            .subscribe(doOnNext, doOnError));
        else {
            adapter.clear();
            offset = 0;
        }
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
        findDoors();
    }

    //method that show high quality image of metal door
    private void showImage(Door door) {
        doorUrl.set(door.getHighQualityDoorUrl());
        showDoor.set(true);
    }

    // hide high quality image after click
    public void hideImage() {
        showDoor.set(false);
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

}
