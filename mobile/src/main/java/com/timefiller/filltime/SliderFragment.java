package com.timefiller.filltime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SliderFragment extends Fragment {

    public SliderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        getActivity().startActivity(new Intent(getContext(), CultureElementActivity.class));
                    }
                }, 400);
            }
        });
        return rootView;
    }

}
