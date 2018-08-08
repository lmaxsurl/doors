package ru.mail1998.logunov.maxim.doors.injection;

import android.content.Context;

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
    public static DoorRepository provideDoorRepository(Context context){
        return new DoorRepositoryImpl(new RestService(), context);
    }

    @Provides
    @Singleton
    public static PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }

}
