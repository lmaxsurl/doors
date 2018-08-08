package ru.mail1998.logunov.maxim.doors.presentation.screens.type_list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.TypesListActivityBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;

public class TypesListActivity extends BaseMvvmActivity<TypesListViewModel,
        TypesListActivityBinding,
        TypesListRouter> {

    private static final String EXTRA_DOOR_CLASS_NAME = "EXTRA_DOOR_CLASS_NAME";

    @Override
    protected TypesListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(TypesListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.types_list_activity;
    }

    @Override
    protected TypesListRouter provideRouter() {
        return new TypesListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setDoorClass(getIntent().getStringExtra(EXTRA_DOOR_CLASS_NAME));
        binding.typesListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.typesListRv.setAdapter(viewModel.adapter);
        binding.typesListRv.setHasFixedSize(true);
    }
}
