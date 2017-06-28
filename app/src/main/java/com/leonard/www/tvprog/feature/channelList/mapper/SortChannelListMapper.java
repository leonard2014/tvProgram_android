package com.leonard.www.tvprog.feature.channelList.mapper;

import com.leonard.www.tvprog.model.Channel;
import com.leonard.www.tvprog.model.Channels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import easymvp.boundary.DataMapper;

/**
 * Created by leoxw on 06/24/2017.
 */

public class SortChannelListMapper extends DataMapper<Channels, List<Channel>> {
    @Inject
    public SortChannelListMapper() {
    }

    @Override
    public List<Channel> apply(Channels channels) throws Exception {
        List<Channel> sorted = new ArrayList<>();
        sorted.addAll(channels.getChannels());

        Collections.sort(sorted,
                (o1, o2) -> o1.getDisplayOrder() - o2.getDisplayOrder());

        return sorted;
    }
}
