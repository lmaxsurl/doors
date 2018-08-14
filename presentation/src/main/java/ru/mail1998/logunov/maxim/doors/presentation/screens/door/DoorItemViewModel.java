package ru.mail1998.logunov.maxim.doors.presentation.screens.door;

import android.databinding.ObservableField;
import logunov.maxim.domain.entity.Door;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewModel;

public class DoorItemViewModel extends BaseItemViewModel<Door> {

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> type = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<String> description = new ObservableField<>("");


    @Override
    public void setItem(Door door, int position) {
        description.set(door.getDescription());
        title.set(door.getTitle());
        type.set(door.getTitle());
        imageUrl.set(door.getDoorUrl());
    }
}
