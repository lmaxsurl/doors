package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
