package ru.mail1998.logunov.maxim.doors.presentation.screens.info;

import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.ICONS8_URL;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.MTS_NUMBER;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.VELCOM_NUMBER;

public class InfoViewModel extends BaseViewModel<InfoRouter> {

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onMtsNumberClick(){
        router.openDial(MTS_NUMBER);
    }

    public void onVelcomNumberClick(){
        router.openDial(VELCOM_NUMBER);
    }

    public void onLinkClick(){
        router.openWebsite(ICONS8_URL);
    }

}
