package com.timefiller.filltime;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.timefiller.filltime.Utili.Code;
import com.timefiller.filltime.views.TextViewCustom;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private String[] gameUrlList = new String[]{
            "http://play.esviji.com"
    };
    private WebView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView minuteTextView = (TextViewCustom) findViewById(R.id.minutes_header);
        String currentValue = getData();
        minuteTextView.setText(currentValue + "'");

        int random = new Random().nextInt(gameUrlList.length);


        gameView = (WebView) findViewById(R.id.gameView);
        WebSettings webSettings = gameView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        gameView.loadUrl(gameUrlList[random]);
    }

    public String getData() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPref.getString(Code.MINUTE, "0");
    }
}
