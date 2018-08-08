package ru.mail1998.logunov.maxim.doors.presentation.screens.type_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.Type;
import ru.mail1998.logunov.maxim.doors.databinding.ItemTypeBinding;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewHolder;

public class TypeItemViewHolder extends BaseItemViewHolder<
        Type,
        TypeItemViewModel,
        ItemTypeBinding> {

    public TypeItemViewHolder(TypeItemViewModel viewModel, ItemTypeBinding binding) {
        super(viewModel, binding);
    }

    public static TypeItemViewHolder create(ViewGroup parent, TypeItemViewModel viewModel) {
        ItemTypeBinding binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new TypeItemViewHolder(viewModel, binding);
    }
}
