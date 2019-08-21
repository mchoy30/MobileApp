package com.example.mchoy.finalprojectmobileapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItineraryItemAdapter extends ArrayAdapter<String>{
    private Context mContext;

    private int mResource;
    public ItineraryItemAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvList = (TextView) convertView.findViewById(R.id.textVeiw1);

        String text = getItem(position).toString();

        tvList.setText(text);

        return convertView;
    }


}
