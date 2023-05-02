package com.example.ciclobnb.Objectes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapAdapter extends ArrayAdapter<String> {

    private final HashMap<Integer, String> mData;

    public HashMapAdapter(Context context, int resource, HashMap<Integer, String> data) {
        super(context, resource, new ArrayList<>(data.values()));
        mData = data;
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mData.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), item))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse((int) -1L);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        String item = getItem(position);
        view.setText(item);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        String item = getItem(position);
        view.setText(item);
        return view;
    }
}
