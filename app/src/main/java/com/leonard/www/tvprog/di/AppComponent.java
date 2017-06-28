package com.leonard.www.tvprog.di;

import com.leonard.www.tvprog.feature.channelList.di.ChannelListModule;
import com.leonard.www.tvprog.feature.channelList.view.ChannelListActivity;
import com.leonard.www.tvprog.feature.program.di.ProgramModule;
import com.leonard.www.tvprog.feature.program.view.ChannelDetailFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leoxw on 06/24/2017.
 */

@Singleton
@Component(modules = {NetModule.class, ChannelListModule.class, ProgramModule.class})
public interface AppComponent {
    void inject(ChannelListActivity channelListActivity);
    void inject(ChannelDetailFragment channelDetailFragment);
}
