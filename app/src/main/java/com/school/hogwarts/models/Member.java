package com.school.hogwarts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {
    private String member;

    public Member() {
    }

    protected Member(Parcel in) {
        member = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(member);
    }
}

