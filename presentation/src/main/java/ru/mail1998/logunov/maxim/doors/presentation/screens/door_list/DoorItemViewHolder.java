package ru.mail1998.logunov.maxim.doors.presentation.screens.door_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.Door;
import ru.mail1998.logunov.maxim.doors.databinding.ItemDoorBinding;
import ru.mail1998.logunov.maxim.doors.databinding.ItemTypeBinding;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewHolder;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypeItemViewHolder;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypeItemViewModel;

public class DoorItemViewHolder extends BaseItemViewHolder <Door, DoorItemViewModel, ItemDoorBinding> {

    public DoorItemViewHolder(DoorItemViewModel viewModel, ItemDoorBinding binding) {
        super(viewModel, binding);
    }

    public static DoorItemViewHolder create(ViewGroup parent, DoorItemViewModel viewModel) {
        ItemDoorBinding binding = ItemDoorBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new DoorItemViewHolder(viewModel, binding);
    }
}
