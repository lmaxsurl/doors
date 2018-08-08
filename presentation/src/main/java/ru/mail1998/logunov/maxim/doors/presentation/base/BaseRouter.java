package ru.mail1998.logunov.maxim.doors.presentation.base;

public abstract class BaseRouter<A extends BaseActivity>  {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void finishActivity(){
        activity.finish();
    }

}
