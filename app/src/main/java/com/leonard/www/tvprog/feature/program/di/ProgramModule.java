package com.leonard.www.tvprog.feature.program.di;

import com.leonard.www.tvprog.feature.program.domain.GetProgramUseCase;
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
public class ProgramModule {
    private UseCaseExecutor useCaseExecutor;
    private PostExecutionThread postExecutionThread;

    public ProgramModule(UseCaseExecutor useCaseExecutor, PostExecutionThread postExecutionThread) {
        this.useCaseExecutor = useCaseExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @Provides
    @Singleton
    GetProgramUseCase provideGetProgramUseCase(ProgramApiClient programApiClient) {
        return new GetProgramUseCase(useCaseExecutor, postExecutionThread, programApiClient);
    }
}
