package ru.mail1998.logunov.maxim.doors.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FixedSizeImageView extends android.support.v7.widget.AppCompatImageView {
    public FixedSizeImageView(Context context) {
        super(context);
    }

    public FixedSizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = MeasureSpec.getSize(widthMeasureSpec) * 0.382f;
        float width = height * 0.5f;
        setMeasuredDimension((int)width, (int)height);
    }
}
