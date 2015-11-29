package com.timefiller.filltime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SliderFragment extends Fragment {

    private String title;
    private String subtitle;
    private String contentPath;
    private int drawable;

    public SliderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        title = args.getString("title");
        subtitle = args.getString("subtitle");
        contentPath = args.getString("contentPath");
        drawable = args.getInt("drawable");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        final ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_slider, container, false);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation pulse = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
                rootView.startAnimation(pulse);
                rootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getContext(), CultureElementActivity.class);
                        intent.putExtra("contentPath", contentPath);
                        getActivity().startActivity(intent);
                    }
                }, 400);
            }
        });

        TextView titleTextView = (TextView) rootView.findViewById(R.id.slider_title);
        titleTextView.setText(title);
        TextView subtitleTextView = (TextView) rootView.findViewById(R.id.slider_subtitle);
        subtitleTextView.setText(subtitle);
        View imgView = rootView.findViewById(R.id.slider_img);
        imgView.setBackground(getResources().getDrawable(drawable));

        return rootView;
    }

}
