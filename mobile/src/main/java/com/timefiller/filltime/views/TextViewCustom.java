package com.timefiller.filltime.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Chai on 28/11/2015.
 */
public class TextViewCustom extends TextView {
    public TextViewCustom(Context context) {
        super(context);
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);

        Typeface dosis_typeface = Typeface.createFromAsset(getContext().getAssets(), "font/dosis_medium.ttf");
        super.setTypeface(dosis_typeface);


    }


}
