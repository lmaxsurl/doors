package ru.mail1998.logunov.maxim.doors.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

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
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
