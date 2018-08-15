package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.util.Log;

import logunov.maxim.domain.entity.Error;
import ru.mail1998.logunov.maxim.doors.R;

public abstract class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public String getErrorMessage(Throwable throwable) {
        if (throwable instanceof Error) {
            Error error = (Error) throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:
                    return activity.getResources().getString(R.string.error_internet_not_available);
                case SERVER_IS_NOT_AVAILABLE:
                    return activity.getResources().getString(R.string.error_server_not_available);
                case SERVER_ERROR:
                    return activity.getResources().getString(R.string.error_server);
                default: {
                    Log.e(activity.getClass().getSimpleName(),
                            "забыл указать тип ошибки " + throwable.toString());
                    return activity.getResources().getString(R.string.something_wrong);
                }
            }
        } else {
            Log.e(activity.getClass().getSimpleName(),
                    "ужасная проблема " + throwable.toString());
            return activity.getResources().getString(R.string.error);
        }
    }
}
