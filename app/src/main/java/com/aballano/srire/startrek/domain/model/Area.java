package com.aballano.srire.startrek.domain.model;

import android.graphics.Color;

public enum Area {
    SCIENCE(Color.BLUE),
    ENGINEERING(Color.RED),
    COMMAND(Color.YELLOW);

    public final int color;

    Area(int color) {
        this.color = color;
    }
}
