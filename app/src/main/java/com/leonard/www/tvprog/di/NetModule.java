package com.leonard.www.tvprog.di;

import com.leonard.www.tvprog.network.ProgramApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leoxw on 06/24/2017.
 */
@Module
public class NetModule {
    final private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    public ProgramApiClient programApiClient() {
        return new ProgramApiClient(baseUrl);
    }
}
