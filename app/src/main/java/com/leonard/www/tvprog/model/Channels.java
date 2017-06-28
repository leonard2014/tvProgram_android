
package com.leonard.www.tvprog.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channels {

    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

}
