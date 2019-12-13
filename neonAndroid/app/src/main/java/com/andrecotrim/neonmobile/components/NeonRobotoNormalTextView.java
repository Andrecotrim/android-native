package com.andrecotrim.neonmobile.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class NeonRobotoNormalTextView extends TextView {
    public NeonRobotoNormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Roboto-Normal.ttf"));
    }
}
