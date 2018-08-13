package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.mail1998.logunov.maxim.doors.BR;

public abstract class BaseMvvmActivity<
        ViewModel extends BaseViewModel,
        B extends ViewDataBinding,
        R extends BaseRouter>
        extends BaseActivity{

    protected ViewModel viewModel;
    protected B binding;
    protected R router;

    /**
     * Use ViewModelProviders.of(this).get(ViewModel.class);
     */

    protected abstract ViewModel provideViewModel();

    protected abstract int provideLayoutId();

    protected abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = provideViewModel();

        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel, viewModel);

        router = provideRouter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.addRouter(router);
        viewModel.setIsConnected(router.checkInternetAccess());
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }
}
