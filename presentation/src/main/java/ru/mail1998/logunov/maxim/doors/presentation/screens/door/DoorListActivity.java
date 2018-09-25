package ru.mail1998.logunov.maxim.doors.presentation.screens.door;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoViewAttacher;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.ActivityDoorListBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;
import ru.mail1998.logunov.maxim.doors.custom.recycler.SimpleDividerItemDecoration;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_TYPE_ID;

public class DoorListActivity extends BaseMvvmActivity<
        DoorListViewModel,
        ActivityDoorListBinding,
        DoorListRouter> {

    public static Intent getIntent(Activity activity, String doorClass, int typeId) {
        Intent intent = new Intent(activity, DoorListActivity.class);
        intent.putExtra(EXTRA_DOOR_CLASS, doorClass);
        intent.putExtra(EXTRA_TYPE_ID, typeId);
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
        init();
    }

    private void init() {
        initRecyclerView();
        initActionBar();
        setDoorsParams();
    }

    private void setDoorsParams() {
        viewModel.setDataParams(getIntent().getStringExtra(EXTRA_DOOR_CLASS),
                getIntent().getIntExtra(EXTRA_TYPE_ID, 0));
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolBar);
    }

    private void initRecyclerView() {
        binding.doorListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.doorListRv.setAdapter(viewModel.adapter);
        binding.doorListRv.setHasFixedSize(true);
        binding.doorListRv.addItemDecoration(new SimpleDividerItemDecoration(this));
        binding.doorListRv.setLimit(viewModel.PAGE_SIZE);
        viewModel.subscribeScrolledItems(binding.doorListRv.observeScrolledData());
    }

    //check if door image is shown
    @Override
    public void onBackPressed() {
        if (viewModel.showDoor.get())
            viewModel.hideImage();
        else
            super.onBackPressed();
    }
}
