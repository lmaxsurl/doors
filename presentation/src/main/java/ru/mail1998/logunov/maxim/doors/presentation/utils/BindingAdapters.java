package ru.mail1998.logunov.maxim.doors.presentation.utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import ru.mail1998.logunov.maxim.doors.R;

public class BindingAdapters {

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.get()
                .load(url)
                .error(error)
                .into(view);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean isVisible){
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
