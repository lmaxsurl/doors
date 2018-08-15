package ru.mail1998.logunov.maxim.doors.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import ru.mail1998.logunov.maxim.doors.injection.AppComponent;
import ru.mail1998.logunov.maxim.doors.injection.AppModule;
import ru.mail1998.logunov.maxim.doors.injection.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        appComponent = DaggerAppComponent
                .builder()
                .build();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
