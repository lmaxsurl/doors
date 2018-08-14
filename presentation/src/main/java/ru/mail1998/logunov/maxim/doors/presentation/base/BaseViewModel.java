package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
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

    public CompositeDisposable getCompositeDisposable() {
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
