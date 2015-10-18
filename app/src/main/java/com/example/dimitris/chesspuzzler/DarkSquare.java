package com.example.dimitris.chesspuzzler;

import android.graphics.Color;
import android.graphics.Rect;


public class DarkSquare extends Square {

    public DarkSquare(String name, Rect rect) {
        super(name, rect);
        itsPaint.setColor(Color.rgb(160, 82, 45));
    }
}
