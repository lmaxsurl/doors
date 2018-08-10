package ru.mail1998.logunov.maxim.doors.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

public class FixedSizeTextView extends android.support.v7.widget.AppCompatTextView {

    private float height;
    private float scale;


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
        int width = getMeasuredWidth();
        height = width * 0.15f;
        setMeasuredDimension(widthMeasureSpec, (int) height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = getHeight();
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (left != null) {
            left.setBounds(0, 0, left.getIntrinsicWidth(),
                    left.getIntrinsicHeight());
            scale = height / left.getIntrinsicHeight();
            ScaleDrawable scaleDrawable = new ScaleDrawable(left, 0, scale, scale);
            left = scaleDrawable.getDrawable();
        }
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

}
