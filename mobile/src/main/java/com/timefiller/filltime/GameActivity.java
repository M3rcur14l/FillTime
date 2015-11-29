package com.timefiller.filltime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GameActivity extends AppCompatActivity {

    private String GameURL = "http://play.esviji.com";
    private WebView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        gameView = (WebView) findViewById(R.id.gameView);
        WebSettings webSettings = gameView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        gameView.loadUrl(GameURL);
    }
}
