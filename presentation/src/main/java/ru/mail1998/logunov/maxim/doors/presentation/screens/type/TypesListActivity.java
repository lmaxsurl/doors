package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.ActivityTypesListBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;
import ru.mail1998.logunov.maxim.doors.custom.recycler.SimpleDividerItemDecoration;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_DOOR_CLASS;

public class TypesListActivity extends BaseMvvmActivity<TypesListViewModel,
        ActivityTypesListBinding,
        TypesListRouter> {

    public static Intent getIntent(Activity activity){
        return new Intent(activity, TypesListActivity.class);
    }

    @Override
    protected TypesListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(TypesListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_types_list;
    }

    @Override
    protected TypesListRouter provideRouter() {
        return new TypesListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecycleView();
        setSupportActionBar(binding.toolBar);
    }

    private void initRecycleView() {
        binding.typesListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.typesListRv.setAdapter(viewModel.adapter);
        binding.typesListRv.setHasFixedSize(true);
        binding.typesListRv.addItemDecoration(new SimpleDividerItemDecoration(this));
        binding.typesListRv.setLimit(viewModel.PAGE_SIZE);
        viewModel.subscribeScrolledItems(binding.typesListRv.observeScrolledData());
    }
}
