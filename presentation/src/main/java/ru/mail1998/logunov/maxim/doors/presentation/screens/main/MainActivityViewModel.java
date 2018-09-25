package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.mail1998.logunov.maxim.doors.R;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.utils.Extras;

import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.METAL_DOOR_TYPES;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.WOOD_DOOR_CLASS;
import static ru.mail1998.logunov.maxim.doors.presentation.utils.Extras.WOOD_DOOR_TYPES;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {
    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public MainActivityViewModel() {
        dismissProgressBar();
    }

    public void onWoodDoorClick(){
        router.showDoorTypes(WOOD_DOOR_TYPES, WOOD_DOOR_CLASS);
    }

    public void onMetalDoorClick(){
        router.showDoorTypes(METAL_DOOR_TYPES, METAL_DOOR_CLASS);
    }

    public void onMapClick(){
        router.openMap();
    }

    public void onInfoClick() {
        router.showInfo();
    }
}
