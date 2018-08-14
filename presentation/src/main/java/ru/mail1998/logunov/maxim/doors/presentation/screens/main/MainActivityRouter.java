package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorListActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.info.InfoActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.map.MapsActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type.TypesListActivity;
import ru.mail1998.logunov.maxim.doors.presentation.utils.Extras;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }

    public void showDoorTypes(String doorClass){
        activity.startActivity(TypesListActivity.getIntent(activity, doorClass));
    }

    public void showMetalDoors(){
        activity.startActivity(DoorListActivity.getIntent(activity, Extras.METAL_DOOR_CLASS, Extras.METAL_DOOR_TYPE));
    }

    public void openMap(){
        activity.startActivity(MapsActivity.getIntent(activity));
    }

    public void showInfo(){
        activity.startActivity(InfoActivity.getIntent(activity));
    }
}
