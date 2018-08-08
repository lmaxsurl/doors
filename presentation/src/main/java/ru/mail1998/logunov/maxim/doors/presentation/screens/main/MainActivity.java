package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import android.arch.lifecycle.ViewModelProviders;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.MainActivityBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<MainActivityViewModel,
        MainActivityBinding,
        MainActivityRouter> {
    @Override
    protected MainActivityViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected MainActivityRouter provideRouter() {
        return new MainActivityRouter(this);
    }
}
