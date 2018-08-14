package ru.mail1998.logunov.maxim.doors.presentation.screens.type;

import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorListActivity;

public class TypesListRouter extends BaseRouter<TypesListActivity> {

    public TypesListRouter(TypesListActivity activity) {
        super(activity);
    }

    // open new activity with necessary door class and type
    public void goToDoorList(String doorClass, String doorType) {
        activity.startActivity(DoorListActivity.getIntent(activity, doorClass, doorType));
        startAnimation();
    }

}
