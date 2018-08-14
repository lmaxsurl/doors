package ru.mail1998.logunov.maxim.doors.presentation.base;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import ru.mail1998.logunov.maxim.doors.R;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null
                && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.increase_alfa, R.anim.compression_with_apha);
    }

}
