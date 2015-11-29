package com.timefiller.filltime;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.jorgecastilloprz.pagedheadlistview.PagedHeadListView;
import com.jorgecastilloprz.pagedheadlistview.utils.PageTransformerTypes;
import com.timefiller.filltime.Utili.Code;
import com.timefiller.filltime.views.TextViewCustom;

import java.util.ArrayList;

public class PagedActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    private PagedHeadListView mPagedHeadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paged);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mPagedHeadList = (PagedHeadListView) findViewById(R.id.pagedHeadListView);

        TextView minuteTextView = (TextViewCustom) findViewById(R.id.minutes_header);
        String currentValue = getData();
        minuteTextView.setText(currentValue + "'");

        Bundle bundle = new Bundle();
        String title = "ERA IL";
        String subtitle = "1989";
        String contentPath = "text/culture_politecnico.txt";
        int drawable = R.drawable.politecnico;
        bundle.putString("title", title);
        bundle.putString("subtitle", subtitle);
        bundle.putString("contentPath", contentPath);
        bundle.putInt("drawable", drawable);
        SliderFragment politecnicoFragment = new SliderFragment();
        politecnicoFragment.setArguments(bundle);

        bundle = new Bundle();
        title = "APPASSIONATI DAL";
        subtitle = "1899";
        contentPath = "text/culture_milan.txt";
        drawable = R.drawable.milan;
        bundle.putString("title", title);
        bundle.putString("subtitle", subtitle);
        bundle.putString("contentPath", contentPath);
        bundle.putInt("drawable", drawable);
        SliderFragment milanFragment = new SliderFragment();
        milanFragment.setArguments(bundle);

        mPagedHeadList.addFragmentToHeader(politecnicoFragment);
        mPagedHeadList.addFragmentToHeader(milanFragment);

        mPagedHeadList.setHeaderOffScreenPageLimit(2);
        mPagedHeadList.setHeaderPageTransformer(PageTransformerTypes.ZOOMOUT);

        ArrayList<String> elementList = new ArrayList<>();

        PageAdapter pageAdapter = new PageAdapter(this, R.layout.element_list_item, elementList);
        mPagedHeadList.setAdapter(pageAdapter);
        mPagedHeadList.setHeaderHeight((int) (metrics.heightPixels * 0.75f));
    }


    public String getData() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPref.getString(Code.MINUTE, "0");
    }
}
