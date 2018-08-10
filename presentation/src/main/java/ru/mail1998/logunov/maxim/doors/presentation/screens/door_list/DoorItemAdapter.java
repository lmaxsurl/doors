package ru.mail1998.logunov.maxim.doors.presentation.screens.door_list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.Door;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewHolder;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseRecyclerViewAdapter;

public class DoorItemAdapter extends BaseRecyclerViewAdapter<Door, DoorItemViewModel> {
    @NonNull
    @Override
    public BaseItemViewHolder<Door, DoorItemViewModel, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DoorItemViewHolder.create(parent, new DoorItemViewModel());
    }
}
