package ru.mail1998.logunov.maxim.doors.presentation.screens.info;

import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;

public class InfoViewModel extends BaseViewModel<InfoRouter> {

    private final String MTS_NUMBER = "+375336339291";
    private final String VELCOM_NUMBER = "+375296798750";
    private final String ICONS8_URL = "https://icons8.ru/";
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
