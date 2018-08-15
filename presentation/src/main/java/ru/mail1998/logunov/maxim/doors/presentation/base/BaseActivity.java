package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import ru.mail1998.logunov.maxim.doors.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.extension_with_alpha, R.anim.reduce_alfa);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.increase_alfa, R.anim.compression_with_apha);
    }

}
