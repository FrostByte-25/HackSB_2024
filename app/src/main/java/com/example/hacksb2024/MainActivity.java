package com.example.hacksb2024;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MediaPlayer> playlist = new ArrayList<>();
    private ArrayList<String> songTitles = new ArrayList<>(Arrays.asList("Track 1", "Track 2", "Track 3", "Track 4", "Track 5", "Track 6", "Track 7", "Track 8", "Track 9", "Track 10"));
    private int currentTrackIndex = 0;
    private boolean isPlaying = false;

    private ListView music;
    private ImageView buttonPlayPause, buttonBack, buttonForward;
    private CountDownTimer breathingTimer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = findViewById(R.id.listView);
        buttonPlayPause = findViewById(R.id.imageView2);
        buttonBack = findViewById(R.id.back);
        buttonForward = findViewById(R.id.forward);

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

        CustomAdapter adapter = new CustomAdapter(this, songTitles);
        music.setAdapter(adapter);

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousTrack();
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTrack();
            }
        });

        startBreathingTimer(10);
    }

    private void togglePlayPause() {
        if (isPlaying) {
            playlist.get(currentTrackIndex).pause();
            buttonPlayPause.setImageResource(R.drawable.play);
        } else {
            playlist.get(currentTrackIndex).start();
            buttonPlayPause.setImageResource(R.drawable.pause);
        }
        isPlaying = !isPlaying;
    }

    private void previousTrack() {
        if (currentTrackIndex > 0) {
            playlist.get(currentTrackIndex).pause();
            currentTrackIndex--;
        } else {
            playlist.get(currentTrackIndex).seekTo(0);
        }
        if (isPlaying) {
            playlist.get(currentTrackIndex).start();
        }
    }

    private void nextTrack() {
        playlist.get(currentTrackIndex).pause();
        currentTrackIndex = (currentTrackIndex + 1) % playlist.size();
        if (isPlaying) {
            playlist.get(currentTrackIndex).start();
        }
    }

    private void startBreathingTimer(int minutes) {
        long duration = minutes * 60 * 1000;
        breathingTimer = new CountDownTimer(duration, 15000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("Breathing", "Breathe In");
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Breathing", "Hold");
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("Breathing", "Breathe Out");
                            }
                        }, 5000);
                    }
                }, 5000);
            }

            @Override
            public void onFinish() {
                Log.d("Breathing", "Breathing session finished");
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (MediaPlayer player : playlist) {
            if (player != null) {
                player.release();
            }
        }
        if (breathingTimer != null) {
            breathingTimer.cancel();
        }
    }
}
