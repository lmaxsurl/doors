package ru.mail1998.logunov.maxim.doors.presentation.screens.type_list;

import android.content.Intent;

import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door_list.DoorListActivity;

public class TypesListRouter extends BaseRouter<TypesListActivity> {

    public TypesListRouter(TypesListActivity activity) {
        super(activity);
    }

    public void goToDoorList(String doorClass, String doorType) {
        activity.startActivity(DoorListActivity.getIntent(activity, doorClass, doorType));
    }

}
