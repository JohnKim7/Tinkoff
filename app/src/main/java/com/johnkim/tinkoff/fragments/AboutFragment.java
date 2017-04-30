package com.johnkim.tinkoff.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.johnkim.tinkoff.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017. 3. 30..
 */

public class AboutFragment extends Fragment {
    LinearLayout logo_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_about, container, false);

        logo_layout = (LinearLayout) layout.findViewById(R.id.logo_layout);

        ImageView imageView = (ImageView) logo_layout.findViewById(R.id.logo);
        imageView.setAlpha(0.0f);
        imageView.animate().alpha(1.0f).setDuration(2000);
        TransitionManager.beginDelayedTransition((ViewGroup) logo_layout);

        return layout;
    }


}
