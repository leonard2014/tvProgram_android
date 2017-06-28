
package com.leonard.www.tvprog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("channelId")
    @Expose
    private Integer channelId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("displayOrder")
    @Expose
    private Integer displayOrder;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}
