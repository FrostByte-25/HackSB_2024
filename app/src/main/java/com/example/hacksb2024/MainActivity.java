package com.example.hacksb2024;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MediaPlayer> playlist = new ArrayList<MediaPlayer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playlist.add(MediaPlayer.create(this, R.raw.track1));
        playlist.add(MediaPlayer.create(this, R.raw.track2));
        playlist.add(MediaPlayer.create(this, R.raw.track3));
        playlist.add(MediaPlayer.create(this, R.raw.track4));
        playlist.add(MediaPlayer.create(this, R.raw.track5));
        playlist.add(MediaPlayer.create(this, R.raw.track6));
        playlist.add(MediaPlayer.create(this, R.raw.track7));
        playlist.add(MediaPlayer.create(this, R.raw.track8));
        playlist.add(MediaPlayer.create(this, R.raw.track9));
        playlist.add(MediaPlayer.create(this, R.raw.track10));

        CustomAdapter adapter = new CustomAdapter(MainActivity.this,R.layout.custom_adapter,playlist);
    }
}