package ru.mail1998.logunov.maxim.doors.presentation.screens.info;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.ActivityInfoBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseActivity;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;

public class InfoActivity extends BaseMvvmActivity<InfoViewModel, ActivityInfoBinding, InfoRouter> {

    public static Intent getIntent(Activity activity){
        return new Intent(activity, InfoActivity.class);
    }

    @Override
    protected InfoViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(InfoViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    protected InfoRouter provideRouter() {
        return new InfoRouter(this);
    }
}
