package com.timefiller.filltime;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

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

        mPagedHeadList.addFragmentToHeader(new SliderFragment());
        mPagedHeadList.addFragmentToHeader(new SliderFragment());

        mPagedHeadList.setHeaderOffScreenPageLimit(2);
        mPagedHeadList.setHeaderPageTransformer(PageTransformerTypes.ZOOMOUT);

        ArrayList<String> elementList = new ArrayList<>();

        PageAdapter pageAdapter = new PageAdapter(this, R.layout.element_list_item, elementList);
        mPagedHeadList.setAdapter(pageAdapter);
        mPagedHeadList.setHeaderHeight((int) (metrics.heightPixels * 0.8f));
    }
}
