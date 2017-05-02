package com.example.material_design_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by doudou on 2017/5/2.
 */

public class LocationFragment extends Fragment {

    private TextView mText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mText = (TextView) view.findViewById(R.id.text);
        mText.setText("location");
    }
}
