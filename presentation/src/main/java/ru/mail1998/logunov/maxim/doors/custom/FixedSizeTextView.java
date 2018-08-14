package ru.mail1998.logunov.maxim.doors.custom;

import android.content.Context;
import android.util.AttributeSet;

public class FixedSizeTextView extends android.support.v7.widget.AppCompatTextView {

    public FixedSizeTextView(Context context) {
        super(context);
    }

    public FixedSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = getMeasuredWidth() * 0.175f;
        setMeasuredDimension(getMeasuredWidth(), (int) height);
    }

}
