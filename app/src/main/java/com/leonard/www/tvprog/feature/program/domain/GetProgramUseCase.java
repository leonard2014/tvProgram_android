package com.leonard.www.tvprog.feature.program.domain;

import com.leonard.www.tvprog.model.Programs;
import com.leonard.www.tvprog.network.ProgramApiClient;

import javax.annotation.Nullable;

import easymvp.executer.PostExecutionThread;
import easymvp.executer.UseCaseExecutor;
import easymvp.usecase.ObservableUseCase;
import io.reactivex.Observable;

/**
 * Created by leoxw on 06/24/2017.
 */

public class GetProgramUseCase extends ObservableUseCase<Programs, Integer> {
    private ProgramApiClient programApiClient;

    public GetProgramUseCase(UseCaseExecutor useCaseExecutor, PostExecutionThread postExecutionThread, ProgramApiClient programApiClient) {
        super(useCaseExecutor, postExecutionThread);
        this.programApiClient = programApiClient;
    }

    @Override
    protected Observable<Programs> interact(@Nullable Integer channelId) {
        return programApiClient.getPrograms(channelId);
    }
}
