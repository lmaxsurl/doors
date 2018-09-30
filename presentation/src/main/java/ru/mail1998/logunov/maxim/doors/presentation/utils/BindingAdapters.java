package ru.mail1998.logunov.maxim.doors.presentation.utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.mail1998.logunov.maxim.doors.R;

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get()
                .load(url)
                .into(view);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean isVisible){
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
