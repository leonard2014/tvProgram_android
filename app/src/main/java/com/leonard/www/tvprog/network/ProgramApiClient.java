package com.leonard.www.tvprog.network;

import com.google.gson.GsonBuilder;
import com.leonard.www.tvprog.model.Channels;
import com.leonard.www.tvprog.model.Programs;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leoxw on 06/24/2017.
 */

public class ProgramApiClient {
    protected String baseUrl;
    protected IProgramService programService;

    public ProgramApiClient(String baseUrl) {
        this.baseUrl = baseUrl;

        programService = buildProgramService(baseUrl);
    }

    private IProgramService buildProgramService(String baseUrl) {
        return new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create())
                )
                .build()
                .create(IProgramService.class);
    }

    public Observable<Channels> getChannels() {
        return programService.getChannels();
    }

    public Observable<Programs> getPrograms(int channelId) {
        return programService.getPrograms(channelId);
    }
}
