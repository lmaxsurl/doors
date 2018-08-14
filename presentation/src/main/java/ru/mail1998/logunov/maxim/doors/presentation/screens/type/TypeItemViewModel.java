package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import android.databinding.ObservableField;

import logunov.maxim.domain.entity.Type;
import ru.mail1998.logunov.maxim.doors.presentation.recycler.BaseItemViewModel;

public class TypeItemViewModel extends BaseItemViewModel<Type> {

    public ObservableField<String> type = new ObservableField<>("");

    @Override
    public void setItem(Type doorType, int position) {
        type.set(doorType.getType());
    }
}

