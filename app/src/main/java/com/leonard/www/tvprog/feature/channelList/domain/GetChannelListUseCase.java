package com.leonard.www.tvprog.feature.channelList.domain;

import com.leonard.www.tvprog.model.Channels;
import com.leonard.www.tvprog.network.ProgramApiClient;

import javax.annotation.Nullable;

import easymvp.executer.PostExecutionThread;
import easymvp.executer.UseCaseExecutor;
import easymvp.usecase.ObservableUseCase;
import io.reactivex.Observable;

/**
 * Created by leoxw on 06/24/2017.
 */

public class GetChannelListUseCase extends ObservableUseCase<Channels, Void> {
    private ProgramApiClient programApiClient;

    public GetChannelListUseCase(UseCaseExecutor useCaseExecutor, PostExecutionThread postExecutionThread, ProgramApiClient programApiClient) {
        super(useCaseExecutor, postExecutionThread);
        this.programApiClient = programApiClient;
    }

    @Override
    protected Observable<Channels> interact(@Nullable Void param) {
        return programApiClient.getChannels();
    }
}
