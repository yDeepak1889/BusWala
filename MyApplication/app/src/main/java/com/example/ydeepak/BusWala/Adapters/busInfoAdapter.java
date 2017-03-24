package com.example.ydeepak.BusWala.Adapters;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ydeepak.BusWala.GeneralInfo.busCurrentInfo;
import com.example.ydeepak.BusWala.R;

import java.util.ArrayList;

/**
 * Created by ydeepak on 24/3/17.
 */

public class busInfoAdapter extends ArrayAdapter<busCurrentInfo> {
    public busInfoAdapter(Activity a, ArrayList<busCurrentInfo> arr) {
        super(a, 0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        busCurrentInfo d;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buseslistitem, parent, false);
        }

        d = getItem(position);
        TextView t1, t2, t3;

        t1 = (TextView) convertView.findViewById(R.id.busName);
        t2 = (TextView) convertView.findViewById(R.id.time);

        t1.setText(d.getName());
        t2.setText(d.getTime());
        return convertView;
    }
}
