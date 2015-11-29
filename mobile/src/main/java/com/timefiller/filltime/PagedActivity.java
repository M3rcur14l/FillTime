package com.timefiller.filltime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.jorgecastilloprz.pagedheadlistview.PagedHeadListView;
import com.jorgecastilloprz.pagedheadlistview.utils.PageTransformerTypes;

import java.util.ArrayList;

public class PagedActivity extends AppCompatActivity {

    private PagedHeadListView mPagedHeadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paged);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mPagedHeadList = (PagedHeadListView) findViewById(R.id.pagedHeadListView);

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
}
