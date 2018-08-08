package ru.mail1998.logunov.maxim.doors.presentation.screens.type_list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.Type;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewHolder;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseRecyclerViewAdapter;

public class TypeItemAdapter extends BaseRecyclerViewAdapter<Type,
        TypeItemViewModel> {
    @NonNull
    @Override
    public BaseItemViewHolder<Type, TypeItemViewModel, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TypeItemViewHolder.create(parent, new TypeItemViewModel());
    }
}
