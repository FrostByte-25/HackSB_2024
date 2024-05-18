package com.example.hacksb2024;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Track>
{
    ArrayList<Track> mPA;
    Context c;
    int xml;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Track> media) {
        super(context, resource, media);
        xml = resource;
        mPA = media;
        c = context;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lF = (LayoutInflater)c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View aL = lF.inflate(xml,null);
        TextView name = aL.findViewById(R.id.name);
        TextView time = aL.findViewById(R.id.time);

        name.setText(mPA.get(position).getName());
        time.setText("3:50");

        return aL;
    }
}
