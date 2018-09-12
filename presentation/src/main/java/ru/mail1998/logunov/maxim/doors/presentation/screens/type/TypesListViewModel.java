package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import logunov.maxim.domain.entity.Type;
import logunov.maxim.domain.usecases.GetListTypeUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.custom.recycler.OffsetAndLimit;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.ClickedItemModel;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.WOOD_DOOR_CLASS;

public class TypesListViewModel extends BaseViewModel<TypesListRouter> {

    public TypeItemAdapter adapter = new TypeItemAdapter();
    private int offset = 0;
    public final int PAGE_SIZE = 5;

    private Consumer<List<Type>> doOnNext = new Consumer<List<Type>>() {
        @Override
        public void accept(List<Type> types) {
            adapter.addItems(types);
            dismissProgressBar();
            isConnected.set(true);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            if (clickedItemModel.getEntity() instanceof Type) {
                router.goToDoorList(WOOD_DOOR_CLASS,
                        ((Type) clickedItemModel.getEntity()).getId());
            }
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

        getData();
    }

    // method that load data from server
    private void getData() {
        getCompositeDisposable().add(
                getListTypeUseCase
                        .getTypes(offset, PAGE_SIZE)
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

    // method that called when button 'try again' was clicked
    public void tryAgainClick() {
        getData();
    }
}
