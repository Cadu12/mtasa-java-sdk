package com.multitheftauto.sdk.model;

public class Color {
    private int red, green, blue;
    private Integer alpha;

    public Color(int red, int green, int blue) {
        this(red, green, blue, null);
    }

    public Color(int red, int green, int blue, Integer alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }
}
