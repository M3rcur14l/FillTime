package com.timefiller.filltime;

import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.timefiller.filltime.Utili.Code;
import com.timefiller.filltime.zxing.CaptureActivity;

import java.io.UnsupportedEncodingException;

public class WaitTimeActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private PendingIntent nfcPendingIntent;
    private IntentFilter[] intentFiltersArray;
    private NfcAdapter nfcAdapter;
    private String waitTime;
    private MinutePicker minutePicker;
    private String minutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_time);
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.wait_time_rootLayout);

        initNFC();

        minutePicker = new MinutePicker(this);
        rootLayout.addView(minutePicker, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        minutePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                minutePicker.update(x, y);
                saveData(minutePicker.getMinutesString());
                minutePicker.invalidate();
                return true;
            }
        });
    }


    public void onQrCodeClick(View v) {

        Intent intent = new Intent(WaitTimeActivity.this, CaptureActivity.class);
        startActivity(intent);

    }

    public void onStartClick(View v) {

        Intent intent = new Intent(WaitTimeActivity.this, MenuActivity.class);
        startActivity(intent);
        this.finish();
    }


    public void saveData(String value) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Code.MINUTE, value);
        editor.commit();
    }

    private void initNFC() {
        Intent nfcIntent = new Intent(this, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        nfcPendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);

        IntentFilter tagIntentFilter =
                new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            tagIntentFilter.addDataType("text/plain");
            intentFiltersArray = new IntentFilter[]{tagIntentFilter};
        } catch (Throwable t) {
            t.printStackTrace();
        }
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // Check if the smartphone has NFC
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_LONG).show();
            finish();
        }
        // Check if NFC is enabled
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "Enable NFC before using the app", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            waitTime = uri.getQueryParameter("waitTime");
            waitTime = waitTime.trim();
            saveData(waitTime);
            minutePicker.update(waitTime);
            minutePicker.invalidate();

        } else {
            try {
                getTagNFC(intent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void getTagNFC(Intent i) throws UnsupportedEncodingException {
        if (i == null)
            return;
        String type = i.getType();
        String action = i.getAction();

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Log.d("Nfc", "Action NDEF Found");
            Parcelable[] parcs = i.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            // List of records
            for (Parcelable p : parcs) {
                NdefMessage msg = (NdefMessage) p;
                final int numRec = msg.getRecords().length;

                NdefRecord[] records = msg.getRecords();
                for (NdefRecord record : records) {
                    byte[] payload = record.getPayload();
                    waitTime = new String(payload, "UTF-8");
                    waitTime = waitTime.trim();
                    saveData(waitTime);
                    minutePicker.update(waitTime);
                    minutePicker.invalidate();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(
                this,
                nfcPendingIntent,
                intentFiltersArray,
                null);
        handleIntent(getIntent());
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Intent menuActivity = new Intent(WaitTimeActivity.this, MenuActivity.class);
                startActivity(menuActivity);
                WaitTimeActivity.this.finish();
                // Handle successful scan
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }
}
