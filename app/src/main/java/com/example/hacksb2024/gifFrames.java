package com.example.hacksb2024;

import java.util.ArrayList;

public class gifFrames {

    ArrayList<Integer> fIds;
    int currentFrame;
    int deltaX;

    public gifFrames(ArrayList<Integer> f)
    {
        fIds = f;
        currentFrame = fIds.size()-1;
        deltaX = 1;
    }
    public int getCurrentFrame() {
        return fIds.get(currentFrame);
    }
    public void nextFrame()
    {
        if(currentFrame == fIds.size()-1)
        {
            currentFrame = fIds.size()-2;
            deltaX*=-1;
        }
        else if(currentFrame == 0)
        {
            currentFrame = 1;
            deltaX*=-1;
        }
        else
        {
            currentFrame+=deltaX;
        }
    }


}
