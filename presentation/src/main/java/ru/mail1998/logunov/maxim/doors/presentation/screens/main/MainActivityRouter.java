package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;
import ru.mail1998.logunov.maxim.doors.presentation.screens.map.MapsActivity;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypesListActivity;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }

    public void showDoorTypes(String doorClass){
        activity.startActivity(TypesListActivity.getIntent(activity, doorClass));
    }

    public void openMap(){
        activity.startActivity(MapsActivity.getIntent(activity));
    }
}
