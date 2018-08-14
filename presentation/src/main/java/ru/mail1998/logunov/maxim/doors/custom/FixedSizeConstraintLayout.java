package ru.mail1998.logunov.maxim.doors.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

public class FixedSizeConstraintLayout extends ConstraintLayout {
    public FixedSizeConstraintLayout(Context context) {
        super(context);
    }

    public FixedSizeConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSizeConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = getMeasuredWidth() * 0.417f;
        setMeasuredDimension(getMeasuredWidth(), (int) height);

    }
}
