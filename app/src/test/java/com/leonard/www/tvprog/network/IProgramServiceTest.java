package com.leonard.www.tvprog.network;

import com.leonard.www.tvprog.base.BaseNetworkTest;
import com.leonard.www.tvprog.model.Channels;
import com.leonard.www.tvprog.model.Programs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by leoxw on 06/24/2017.
 */
@Config(sdk = 23)
@RunWith(RobolectricTestRunner.class)
public class IProgramServiceTest extends BaseNetworkTest{
    IProgramService programService;
    Channels channelsResult;
    Programs programsResult;

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
    public void getChannels() throws Exception {
        String response = fileToString("channel_list.json");
        setupResponse(response);
        programService
                .getChannels()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> lock.countDown())
                .subscribe(channels -> channelsResult = channels,
                        error -> assertFalse(true));

        lock.await(WAITING, TimeUnit.MILLISECONDS);

        assertEquals(channelsResult.getChannels().size(), 5);
        assertEquals((long) channelsResult.getChannels().get(4).getChannelId(),5);
    }

    @Test
    public void getPrograms() throws Exception {
        String response = fileToString("channel_program_3.json");
        setupResponse(response);
        programService
                .getPrograms(3)
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> lock.countDown())
                .subscribe(programs -> programsResult = programs,
                        error ->assertFalse(true));

        lock.await(WAITING, TimeUnit.MILLISECONDS);

        assertEquals(programsResult.getChannelId(), 3);
        assertEquals(programsResult.getPrograms().size(), 7);
        assertEquals(programsResult.getPrograms().get(5).getSynopsis(), "Vestibulum porttitor mauris eu ipsum bibendum tristique. Maecenas dignissim est a lectus mollis, a lacinia arcu hendrerit.");
    }
}