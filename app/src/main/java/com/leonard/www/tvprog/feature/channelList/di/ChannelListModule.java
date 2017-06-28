package com.leonard.www.tvprog.feature.channelList.di;

import com.leonard.www.tvprog.feature.channelList.domain.GetChannelListUseCase;
import com.leonard.www.tvprog.network.ProgramApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import easymvp.executer.PostExecutionThread;
import easymvp.executer.UseCaseExecutor;

/**
 * Created by leoxw on 06/24/2017.
 */
@Module
public class ChannelListModule {
    private UseCaseExecutor useCaseExecutor;
    private PostExecutionThread postExecutionThread;

    public ChannelListModule(UseCaseExecutor useCaseExecutor, PostExecutionThread postExecutionThread) {
        this.useCaseExecutor = useCaseExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @Provides
    @Singleton
    GetChannelListUseCase provideGetChannelListUseCase(ProgramApiClient programApiClient) {
        return new GetChannelListUseCase(useCaseExecutor, postExecutionThread, programApiClient);
    }
}
