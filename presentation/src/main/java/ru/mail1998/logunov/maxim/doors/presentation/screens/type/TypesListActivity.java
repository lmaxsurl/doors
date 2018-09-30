package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.databinding.ActivityTypesListBinding;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseMvvmActivity;
import ru.mail1998.logunov.maxim.doors.custom.recycler.SimpleDividerItemDecoration;
import ru.mail1998.logunov.maxim.doors.presentation.screens.search.SearchDoorsActivity;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.EXTRA_DOOR_TYPES;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.WOOD_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.WOOD_DOOR_TYPES;

public class TypesListActivity extends BaseMvvmActivity<TypesListViewModel,
        ActivityTypesListBinding,
        TypesListRouter> {

    public static Intent getIntent(Activity activity, String doorType, String doorClass) {
        Intent intent = new Intent(activity, TypesListActivity.class);
        intent.putExtra(EXTRA_DOOR_TYPES, doorType);
        intent.putExtra(EXTRA_DOOR_CLASS, doorClass);
        return intent;
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
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                startActivity(SearchDoorsActivity.getIntent(
                        this, getIntent().getStringExtra(EXTRA_DOOR_CLASS)));
                return true;
        }
        return false;
    }

    private void init() {
        initRecycleView();
        setSupportActionBar(binding.toolBar);
        setDoorParams();
    }

    private void setDoorParams() {
        viewModel.setParams(getIntent().getStringExtra(EXTRA_DOOR_CLASS),
                getIntent().getStringExtra(EXTRA_DOOR_TYPES));
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
