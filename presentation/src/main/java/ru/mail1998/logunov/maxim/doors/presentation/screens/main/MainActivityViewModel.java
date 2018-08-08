package ru.mail1998.logunov.maxim.doors.presentation.screens.main;

import javax.inject.Inject;

import logunov.maxim.domain.usecases.GetListDescriptionUseCase;
import ru.mail1998.logunov.maxim.doors.app.App;
import ru.mail1998.logunov.maxim.doors.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {
    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Inject
    public GetListDescriptionUseCase getListDescriptionUseCase;

    public MainActivityViewModel() {
        getListDescriptionUseCase
                .getDescriptions();
    }

    public void onClick(){

    }
}
