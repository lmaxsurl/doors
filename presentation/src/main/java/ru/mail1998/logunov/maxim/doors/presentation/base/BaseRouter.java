package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import logunov.maxim.domain.entity.Error;
import ru.mail1998.logunov.maxim.doors.R;

import static logunov.maxim.domain.entity.ErrorType.*;

public abstract class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void finishActivity() {
        activity.finish();
    }

    public void showError(Throwable throwable) {
        if (throwable instanceof Error) {
            Error error = (Error) throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:
                    showToastError(R.string.error_internet_not_available);
                    break;
                case SERVER_IS_NOT_AVAILABLE:
                    showToastError(R.string.error_server_not_available);
                    break;
                case SERVER_ERROR:
                    showToastError(R.string.error_server);
                    break;
                default: {
                    Log.e(activity.getClass().getSimpleName(),
                            "забыл указать тип ошибки " + throwable.toString());
                    showToastError(R.string.error);
                }
            }
        } else {
            Log.e(activity.getClass().getSimpleName(),
                    "ужасная проблема " + throwable.toString());
            showToastError(R.string.error);
        }
    }

    private void showToastError(int messageErrorId) {
        Toast.makeText(activity, messageErrorId, Toast.LENGTH_SHORT)
                .show();
    }

    public boolean checkInternetAccess() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager != null &&
                connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
