package com.timefiller.filltime.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.timefiller.filltime.zxing.CameraManager;

/**
 * View for displaying bounds for active camera region
 */
public class BoundingView extends View {
    /**
     * Camera manager
     */
    private CameraManager cameraManager;
    private int width;
    private int height;

    public BoundingView(Context context, int width, int height) {

        super(context);
        this.width = width;
        this.height = height;
    }

    public void setDimension(int width, int height) {
/*
        this.width = width;
        this.height = height;*/
    }

    public BoundingView(Context context, AttributeSet attrs) {

        super(context, attrs);
 /*       ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        this.width = layoutParams.width;
        this.height = layoutParams.height;*/
    }

    /**
     * Sets camera manger
     *
     * @param cameraManager
     */
    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if (cameraManager != null) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Style.FILL);
                paint.setStrokeWidth(3);
                paint.setColor(Color.parseColor("#43C9A800"));

                //Rect boundingRect = cameraManager.getBoundingRect();
                Rect boundingRect = new Rect(10, 10, getWidth() - 10, getHeight() - 10); //left top righ bottom
                canvas.drawRect(boundingRect, paint);
            }
        } catch (Exception e) {

        }
    }
}
