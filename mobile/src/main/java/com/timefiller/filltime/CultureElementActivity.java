package com.timefiller.filltime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class CultureElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_element);
        Intent intent = getIntent();
        String contentPath = intent.getStringExtra("contentPath");

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
}
