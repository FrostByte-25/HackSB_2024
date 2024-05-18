package com.example.hacksb2024;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<Track> playlist = new ArrayList<Track>();

    ListView music;
    ArrayList<Integer> frameIds = new ArrayList<Integer>();
    ImageView ani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = findViewById(R.id.listView);


        playlist.add(new Track("Chakra Balancing Soundscape",MediaPlayer.create(this, R.raw.track1)));
        playlist.add(new Track("Crown Chakra Connection",MediaPlayer.create(this, R.raw.track2)));
        playlist.add(new Track("Third Eye Opening",MediaPlayer.create(this, R.raw.track3)));
        playlist.add(new Track("Throat Chakra Expression",MediaPlayer.create(this, R.raw.track4)));
        playlist.add(new Track("Heart Chakra Healing",MediaPlayer.create(this, R.raw.track5)));
        playlist.add(new Track("Solar Plexus Harmony",MediaPlayer.create(this, R.raw.track6)));
        playlist.add(new Track("Sacral Chakra Flow",MediaPlayer.create(this, R.raw.track7)));
        playlist.add(new Track("Chakra Meditation Music",MediaPlayer.create(this, R.raw.track8)));
        playlist.add(new Track("Align Your Chakras",MediaPlayer.create(this, R.raw.track9)));
        playlist.add(new Track("Root Chakra Awakening",MediaPlayer.create(this, R.raw.track10)));



        CustomAdapter adapter = new CustomAdapter(MainActivity.this,R.layout.custom_adapter,playlist);
        music.setAdapter(adapter);

        ani = findViewById(R.id.imageView);

        frameIds.add(R.drawable.rose_ani2);
        frameIds.add(R.drawable.rose_ani3);
        frameIds.add(R.drawable.rose_ani4);
        frameIds.add(R.drawable.rose_ani5);
        frameIds.add(R.drawable.rose_ani6);
        frameIds.add(R.drawable.rose_ani7);
        frameIds.add(R.drawable.rose_ani8);
        frameIds.add(R.drawable.rose_ani9);
        frameIds.add(R.drawable.rose_ani10);
        frameIds.add(R.drawable.rose_ani11);
        frameIds.add(R.drawable.rose_ani12);

        gifFrames frames = new gifFrames(frameIds);


        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ani.setImageResource(frames.getCurrentFrame());
                frames.nextFrame();
            }
        }, 0, 455);
    }
}