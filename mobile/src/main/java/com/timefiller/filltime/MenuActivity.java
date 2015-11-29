package com.timefiller.filltime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.timefiller.filltime.views.TextViewCustom;

import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity {

    private TextViewCustom minuteCounter;
    private int currentValue = 0;
    private int MAX_VALUE = 15;

    private Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        minuteCounter = (TextViewCustom) findViewById(R.id.minutes_header);
        currentValue = MAX_VALUE;

        myTimer = new Timer();

    }

    /**
     * At each second decrement the minute counter
     **/
    public void starCounteDown(TextViewCustom minuteCounter) {
        final TextViewCustom counter = minuteCounter;

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentValue = currentValue - 1;
                        if (currentValue <= 0) currentValue = 0;
                        String value = currentValue + "'";
                        if (counter != null)
                            counter.setText(value);

                    }
                });

            }
        }, 0, 60000);
    }

    @Override
    public void onPause() {
        super.onPause();
        myTimer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        starCounteDown(minuteCounter);
    }

    public void onMenuItemClick(View v) {
        switch (v.getId()) {
            case R.id.btn_news:
                break;
            case R.id.btn_read:
                break;
            case R.id.btn_culture:
                break;
            case R.id.btn_food:
                break;
            case R.id.btn_game:
                break;
            case R.id.btn_random:
                break;
        }
    }

    public void onRandomClick(View v) {

    }


}
