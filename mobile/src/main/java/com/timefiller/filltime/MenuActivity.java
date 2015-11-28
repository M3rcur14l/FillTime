package com.timefiller.filltime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
}
