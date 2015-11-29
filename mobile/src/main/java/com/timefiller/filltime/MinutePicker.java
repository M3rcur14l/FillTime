package com.timefiller.filltime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by antonello on 28/11/15.
 */
public class MinutePicker extends RelativeLayout {

    private Paint p;
    private TextView minutesTextView;
    private int width, height, radius, centerX, centerY, degree;
    private String minutesString;


    public MinutePicker(Context context) {
        super(context);
        setWillNotDraw(false);
        p = new Paint();
        p.setColor(Color.LTGRAY);
        minutesString = "00";
        minutesTextView = new TextView(getContext());
        minutesTextView.setTextSize(50);
        minutesTextView.setText(minutesString);
        minutesTextView.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(CENTER_VERTICAL);
        addView(minutesTextView, params);
    }

    public MinutePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        p = new Paint();
        p.setColor(Color.LTGRAY);
        minutesString = "00";
        minutesTextView = new TextView(getContext());
        minutesTextView.setTextSize(50);
        minutesTextView.setText(minutesString);
        minutesTextView.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(CENTER_VERTICAL);
        addView(minutesTextView, params);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        if (width < height)
            radius = width / 2;
        else
            radius = height / 2;
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas c) {
        p.setColor(Color.parseColor("#1E8ACFE0"));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        for (int i = 0; i < radius; i += 5) {
            c.drawCircle(centerX, centerY, i, p);
        }
        p.setColor(Color.parseColor("#508ACFE0"));
        p.setStrokeWidth(5);
        c.drawCircle(centerX, centerY, radius, p);
        int verticalMargin = height / 2 - radius;
        int horizontalMargin = width / 2 - radius;
        RectF circleBounds = new RectF(
                horizontalMargin,
                verticalMargin,
                width - horizontalMargin,
                height - verticalMargin);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.parseColor("#8ACFE0"));
        c.drawCircle(centerX, centerY, 70, p);
        c.drawArc(circleBounds, -90, degree, true, p);
    }

    public void update(float x, float y) {
        double tx = x - centerX, ty = y - centerY;
        double t_length = Math.sqrt(tx * tx + ty * ty);
        if (x > centerX) {
            double a = Math.acos(ty / t_length);
            degree = 180 - ((int) Math.toDegrees(a));
        } else {
            double a = Math.acos(ty / t_length);
            degree = 180 + ((int) Math.toDegrees(a));
        }

        //degree = (int) x * 360 / width;
        if (degree < 0)
            degree = 0;
        int minutes = degree / 6;
        if (minutes < 0)
            minutes = 0;
        else if (minutes > 60)
            minutes = 60;
        minutesString = String.format("%02d", minutes);
        ((TextView) getChildAt(0)).setText(minutesString);
    }

    public void update(String minutesString) {
        minutesString = minutesString.trim();
        int minutes = Integer.parseInt(minutesString);
        degree = minutes * 6;
        this.minutesString = String.format("%02d", minutes);
        ((TextView) getChildAt(0)).setText(this.minutesString);
    }
}
