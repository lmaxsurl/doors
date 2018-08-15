package ru.mail1998.logunov.maxim.doors.presentation.screens.info;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import ru.mail1998.logunov.maxim.doors.presentation.base.BaseRouter;

public class InfoRouter extends BaseRouter<InfoActivity> {

    public InfoRouter(InfoActivity activity) {
        super(activity);
    }

    // open dial with selected number
    public void openDial(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        activity.startActivity(intent);
    }

    // open website
    public void openWebsite(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

}
