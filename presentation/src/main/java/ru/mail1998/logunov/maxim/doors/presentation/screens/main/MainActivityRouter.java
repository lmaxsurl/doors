package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorListActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.info.InfoActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.map.MapsActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type.TypesListActivity;
import ru.mail1998.logunov.maxim.doors.presentation.utils.Extras;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_TYPE;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }

    // open activity with types of wood doors
    public void showDoorTypes(){
        activity.startActivity(TypesListActivity.getIntent(activity));
    }

    // open activity with metal doors
    public void showMetalDoors(){
        activity.startActivity(DoorListActivity.getIntent(activity, METAL_DOOR_CLASS, METAL_DOOR_TYPE));
    }

    //open map with marker
    public void openMap(){
        activity.startActivity(MapsActivity.getIntent(activity));
    }

    //open activity with shop info
    public void showInfo(){
        activity.startActivity(InfoActivity.getIntent(activity));
    }
}
