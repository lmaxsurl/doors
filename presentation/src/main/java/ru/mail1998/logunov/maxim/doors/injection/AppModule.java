package ru.mail1998.logunov.maxim.doors.injection;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.data.network.RestService;
import logunov.maxim.data.repositories.DoorRepositoryImpl;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.DoorRepository;
import ru.mail1998.logunov.maxim.doors.executor.UIThread;

@Module
public class AppModule {

    private Context context;
    private static final int TIMEOUT = 10;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Singleton
    @Provides
    public static DoorRepository provideDoorRepository(){
        return new DoorRepositoryImpl(new RestService(provideServerUrl(), TIMEOUT));
    }

    @Provides
    @Singleton
    public static PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }

    @Provides
    @Named("SERVER_URL")
    public static String provideServerUrl(){
        return "https://api.backendless.com/1EBA3425-DB44-22DD-FFF0-1F00CF757E00/16478945-A0BC-408D-FF5E-C4D6CA9F4800/";
    }

}
