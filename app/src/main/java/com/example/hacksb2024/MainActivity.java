package com.example.hacksb2024;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    ArrayList<Track> playlist = new ArrayList<Track>();

    ListView music;
    ArrayList<Integer> frameIds = new ArrayList<Integer>();
    ImageView ani;


    ImageView play,forward,back;

    MediaPlayer player;

    int currentPlaying = -1;

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

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPlaying == -1)
                {
                    currentPlaying = 0;
                    player = playlist.get(0).getMediaPlayer();
                    player.setOnCompletionListener(MainActivity.this);
                    player.start();
                }
                else if(player.isPlaying())
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
                if(currentPlaying == -1)
                {
                    currentPlaying = 0;
                    player = playlist.get(0).getMediaPlayer();
                    player.setOnCompletionListener(MainActivity.this);
                    player.start();
                }
                else {
                    player.stop();
                    if (currentPlaying == playlist.size() - 1) {
                        try {
                            preparePls();
                        } catch (Exception e) {
                            Log.d("prepare error", e.getMessage());
                        }
                        currentPlaying = 0;
                    } else {
                        currentPlaying++;
                        try {
                            preparePls();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    player = playlist.get(currentPlaying).getMediaPlayer();
                    player.start();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPlaying == -1)
                {
                    currentPlaying = 0;
                    player = playlist.get(0).getMediaPlayer();
                    player.setOnCompletionListener(MainActivity.this);
                    player.start();
                }
                else {
                    player.stop();
                    if (currentPlaying == 0) {
                        try {
                            preparePls();
                        } catch (Exception e) {
                            Log.d("prepare error", e.getMessage());
                        }
                        currentPlaying = playlist.size() - 1;
                    }
                    else {
                        currentPlaying--;
                        try {
                            preparePls();
                        } catch (Exception e) {
                            Log.d("prepare error", e.getMessage());
                        }
                    }
                    player = playlist.get(currentPlaying).getMediaPlayer();
                    player.start();
                    Log.d("back button test", "Is playing: " + currentPlaying);
                }
            }
        });

        music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(player != null)
                {
                    player.stop();
                    player.setOnCompletionListener(null);
                }
                currentPlaying = position;
                player = playlist.get(position).getMediaPlayer();
                player.setOnCompletionListener(MainActivity.this);

                player.start();
            }
        });



    }

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
        player.start();
    }

    public void preparePls(){
        for(int i = 0; i < playlist.size(); i++) {
            try {
                playlist.get(i).getMediaPlayer().prepare();
            } catch (Exception e) {
                Log.d("prepare error", e.getMessage());
            }
        }

    }
}