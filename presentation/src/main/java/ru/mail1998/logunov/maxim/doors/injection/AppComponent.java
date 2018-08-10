package ru.mail1998.logunov.maxim.doors.injection;


import javax.inject.Singleton;

import dagger.Component;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door_list.DoorListViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type_list.TypesListViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.main.MainActivityViewModel;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(MainActivityViewModel mainActivityViewModel);
    void runInject(TypesListViewModel typesListViewModel);
    void runInject(DoorListViewModel doorListViewModel);
}
