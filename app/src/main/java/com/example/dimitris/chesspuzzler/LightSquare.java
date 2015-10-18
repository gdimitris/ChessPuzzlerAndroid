package com.example.dimitris.chesspuzzler;

import android.graphics.Color;
import android.graphics.Rect;


public class LightSquare extends Square {

    public LightSquare(String name, Rect rect) {
        super(name, rect);
        itsPaint.setColor(Color.rgb(255, 222, 173));
    }
}
