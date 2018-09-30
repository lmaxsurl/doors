package ru.mail1998.logunov.maxim.doors.presentation.screens.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import logunov.maxim.domain.entity.Door;
import logunov.maxim.domain.usecases.FindDoorsUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorItemAdapter;

public class SearchDoorsViewModel extends BaseViewModel<SearchDoorsRouter> {

    private String doorsClass;
    private int offset = 0;
    private String request = "";
    public final int PAGE_SIZE = 10;
    public DoorItemAdapter adapter = new DoorItemAdapter();
    public ObservableBoolean showDoor = new ObservableBoolean(false);
    public ObservableField<String> doorUrl = new ObservableField<>("n");

    private Consumer<List<Door>> doOnNext = new Consumer<List<Door>>() {
        @Override
        public void accept(List<Door> doors) {
            if (haveRequest())
                adapter.addItems(doors);
            hideProgressBar();
            isConnected.set(true);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            showImage((Door) clickedItemModel.getEntity());
        }
    };

    private Consumer<Integer> doOnScroll = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            offset = integer;
            findDoors();
        }
    };

    @Inject
    public FindDoorsUseCase findDoorsUseCase;

    //set params for loading data
    public void setDataParams(String doorsClass) {
        this.doorsClass = doorsClass;

        getCompositeDisposable().add(
                adapter.observeItemClick()
                        .subscribe(doOnClick, doOnError));
    }

    public void onTextChanged(CharSequence s) {
        clearRequestHistory();
        setRequest(s);
        showProgressBar();
        findDoors();
    }

    //method that load data
    private void findDoors() {
        if (haveRequest()) {
            getCompositeDisposable().add(
                    findDoorsUseCase
                            .findDoors(doorsClass, request, offset, PAGE_SIZE)
                            .subscribe(doOnNext, doOnError));
        } else {
            hideProgressBar();
        }
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

    private void clearRequestHistory() {
        clearRequest();
        clearAdapter();
        nullifyOffset();
    }

    private void clearRequest() {
        request = "";
    }

    private void setRequest(CharSequence s) {
        this.request = s.toString();
    }

    private void nullifyOffset() {
        offset = 0;
    }

    private void clearAdapter() {
        adapter.clear();
    }

    private boolean haveRequest() {
        return request.length() > 0;
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }
}