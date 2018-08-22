package com.example.xuanlan.nightwatchman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemFragment extends Fragment {

    public SystemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.system_main, container, false);
        TextView textView = (TextView) v.findViewById(R.id.system_text);
        textView.setText("系统");
        return  v;
    }
}
