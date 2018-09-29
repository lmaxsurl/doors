package ru.mail1998.logunov.maxim.doors.presentation.screens.search;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.custom.recycler.SimpleDividerItemDecoration;
import ru.mail1998.logunov.maxim.doors.databinding.ActivitySearchBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_DOOR_CLASS;

public class SearchDoorsActivity extends BaseMvvmActivity<
        SearchDoorsViewModel,
        ActivitySearchBinding,
        SearchDoorsRouter> {

    public static Intent getIntent(Activity activity, String doorClass){
        Intent intent = new Intent(activity, SearchDoorsActivity.class);
        intent.putExtra(EXTRA_DOOR_CLASS, doorClass);
        return intent;
    }

    @Override
    protected SearchDoorsViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(SearchDoorsViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchDoorsRouter provideRouter() {
        return new SearchDoorsRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initRecyclerView();
        initActionBar();
        setDataParams();
    }

    private void setDataParams() {
        viewModel.setDataParams(getIntent().getStringExtra(EXTRA_DOOR_CLASS));
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

    @Override
    public void onBackPressed() {
        if (viewModel.showDoor.get())
            viewModel.hideImage();
        else
            super.onBackPressed();
    }
}
