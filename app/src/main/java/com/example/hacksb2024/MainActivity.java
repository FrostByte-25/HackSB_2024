package com.example.hacksb2024;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Track> playlist = new ArrayList<Track>();

    ListView music;

    ImageView play,forward,back;

    MediaPlayer player;

    int currentPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = findViewById(R.id.listView);
        play = findViewById(R.id.imageView2);
        forward = findViewById(R.id.imageView4);
        back = findViewById(R.id.imageView3);


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

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying())
                {
                    play.setImageResource(R.drawable.play);
                    player.pause();
                }
                else
                {
                    play.setImageResource(R.drawable.pause);
                    player.start();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                if(currentPlaying == playlist.size()-1)
                {
                    currentPlaying = 0;
                }
                else
                {
                    currentPlaying++;
                }
                player = playlist.get(currentPlaying).getMediaPlayer();
                player.start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                if(currentPlaying == 0)
                {
                    currentPlaying = playlist.size()-1;
                }
                else
                {
                    currentPlaying--;
                }
                player = playlist.get(currentPlaying).getMediaPlayer();
                player.start();
            }
        });

        music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(player != null)
                {
                    player.stop();
                }
                currentPlaying = position;
                player = playlist.get(position).getMediaPlayer();

                player.start();
            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(currentPlaying == playlist.size()-1)
                {
                    currentPlaying = 0;
                }
                else
                {
                    currentPlaying++;
                }
                player = playlist.get(currentPlaying).getMediaPlayer();
            }
        });




    }
}