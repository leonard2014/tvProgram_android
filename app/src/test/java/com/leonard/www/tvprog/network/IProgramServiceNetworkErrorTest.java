package com.leonard.www.tvprog.network;

import com.leonard.www.tvprog.base.BaseTest;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by leoxw on 06/26/2017.
 */

public class IProgramServiceNetworkErrorTest extends BaseTest {
    protected String serverUrl = "https://127.0.0.2:1234/";
    IProgramService programService;
    boolean isGetError = false;

    @Before
    public void setupService() throws Exception {
        programService = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IProgramService.class);
    }

    @Test
    public void getChannelsError() throws Exception {
        programService
                .getChannels()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> lock.countDown())
                .subscribe(channels -> assertFalse(true),
                        error -> isGetError = true);

        lock.await(WAITING, TimeUnit.MILLISECONDS);

        assertEquals(isGetError, true);
    }

    @Test
    public void getProgramsError() throws Exception {
        programService
                .getPrograms(3)
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> lock.countDown())
                .subscribe(programs ->  assertFalse(true),
                        error -> isGetError = true);

        lock.await(WAITING, TimeUnit.MILLISECONDS);

        assertEquals(isGetError, true);
    }
}
