
package com.leonard.www.tvprog.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programs {

    @SerializedName("channelId")
    @Expose
    private int channelId;
    @SerializedName("programs")
    @Expose
    private List<Program> programs = null;

    public Programs(int channelId, List<Program> programs) {
        this.channelId = channelId;
        this.programs = programs;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

}
