package ru.mail1998.logunov.maxim.doors.injection;


import javax.inject.Singleton;

import dagger.Component;
import ru.mail1998.logunov.maxim.doors.presentation.screens.door.DoorListViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.info.InfoViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.search.SearchDoorsViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.type.TypesListViewModel;
import ru.mail1998.logunov.maxim.doors.presentation.screens.main.MainActivityViewModel;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(MainActivityViewModel mainActivityViewModel);
    void runInject(TypesListViewModel typesListViewModel);
    void runInject(DoorListViewModel doorListViewModel);
    void runInject(InfoViewModel infoViewModel);
    void runInject(SearchDoorsViewModel searchDoorsViewModel);
}
