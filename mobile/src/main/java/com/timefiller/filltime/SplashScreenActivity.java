package com.timefiller.filltime;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseLongArray;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int PAUSE_TIME = 5000;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Timer tm = new Timer();
        TimerTask appLauncher = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, WaitTimeActivity.class));
                SplashScreenActivity.this.finish();
            }
        };
        tm.schedule(appLauncher, PAUSE_TIME);
    }
}
