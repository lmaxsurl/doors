package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.Error;
import ru.mail1998.logunov.maxim.doors.R;

public abstract class BaseViewModel<R extends BaseRouter> extends ViewModel {

    private CompositeDisposable compositeDisposable;

    protected R router;

    protected abstract void runInject();

    public ObservableBoolean progressBar = new ObservableBoolean(true);

    public ObservableBoolean isConnected = new ObservableBoolean(true);

    public ObservableField<String> errorMessage = new ObservableField<>("");

    public void showProgressBar() {
        progressBar.set(true);
    }

    public void dismissProgressBar() {
        progressBar.set(false);
    }

    public BaseViewModel() {
        runInject();
    }

    public void addRouter(R router) {
        this.router = router;
    }

    public void removeRouter() {
        router = null;
    }

    protected Consumer<Throwable> doOnError = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) {
            showErrorMessage(throwable);
        }
    };

    //show error and hide other views
    private void showErrorMessage(Throwable throwable) {
        errorMessage.set(router.getErrorMessage(throwable));
        isConnected.set(false);
    }

    protected CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }
}
