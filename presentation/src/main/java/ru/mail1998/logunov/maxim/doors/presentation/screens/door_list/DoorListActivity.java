package ru.mail1998.logunov.maxim.doors.presentation.screens.door_list;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.ActivityDoorListBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypesListActivity;

import static ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypesListActivity.EXTRA_DOOR_CLASS;

public class DoorListActivity extends BaseMvvmActivity<
        DoorListViewModel,
        ActivityDoorListBinding,
        DoorListRouter> {

    private static final String EXTRA_DOOR_TYPE = "EXTRA_DOOR_TYPE";

    public static Intent getIntent(Activity activity, String doorClass, String doorType){
        Intent intent = new Intent(activity, DoorListActivity.class);
        intent.putExtra(EXTRA_DOOR_CLASS, doorClass);
        intent.putExtra(EXTRA_DOOR_TYPE, doorType);
        return intent;
    }


    @Override
    protected DoorListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(DoorListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_door_list;
    }

    @Override
    protected DoorListRouter provideRouter() {
        return new DoorListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setDoorsParams(getIntent().getStringExtra(EXTRA_DOOR_CLASS),
                getIntent().getStringExtra(EXTRA_DOOR_TYPE));
        binding.doorListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.doorListRv.setAdapter(viewModel.adapter);
        binding.doorListRv.setHasFixedSize(true);
    }
}
