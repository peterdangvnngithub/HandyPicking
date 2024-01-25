package com.example.handypicking.model;

import com.google.gson.annotations.SerializedName;

public class ServerVersion {
    @SerializedName("version")
    private int version;

    public int getVersion() {
        return version;
    }
}