package com.school.hogwarts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Color implements Parcelable {
    private String color;

    public Color() {
    }

    protected Color(Parcel in) {
        color = in.readString();
    }

    public static final Creator<Color> CREATOR = new Creator<Color>() {
        @Override
        public Color createFromParcel(Parcel in) {
            return new Color(in);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(color);
    }
}

