package com.andrecotrim.neonmobile.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class NeonCatamaramRegularTextView extends TextView {
    public NeonCatamaramRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Catamaran-Regular.ttf"));
    }
}
