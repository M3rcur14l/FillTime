package com.timefiller.filltime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.timefiller.filltime.Utili.Code;
import com.timefiller.filltime.views.TextViewCustom;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class CultureElementActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_element);
        Intent intent = getIntent();
        String contentPath = intent.getStringExtra("contentPath");


        TextView minuteTextView = (TextViewCustom) findViewById(R.id.minutes_header);
        String currentValue = getData();
        minuteTextView.setText(currentValue + "'");


        StringBuilder content = new StringBuilder();
        try {
            Scanner scanner = new Scanner(getAssets().open(contentPath));
            while (scanner.hasNext()) {
                content.append(scanner.nextLine());
                content.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView contentTextView = (TextView) findViewById(R.id.culture_text);
        contentTextView.setText(content);


    }

    public String getData() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPref.getString(Code.MINUTE, "0");
    }
}
