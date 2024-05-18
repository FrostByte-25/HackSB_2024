package com.example.hacksb2024;

import android.media.MediaPlayer;

public class Track
{
    String name;
    MediaPlayer mediaPlayer;
    int duration;
    public Track(String n, MediaPlayer mP)
    {
        name = n;
        mediaPlayer = mP;
        duration = mP.getDuration();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
