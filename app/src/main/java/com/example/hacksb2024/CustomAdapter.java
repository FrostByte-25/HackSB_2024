package com.example.hacksb2024;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, ArrayList<String> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter, parent, false);
        }

        String song = getItem(position);
        TextView textViewItem = convertView.findViewById(R.id.textViewItem);
        textViewItem.setText(song);

        return convertView;
    }
}

