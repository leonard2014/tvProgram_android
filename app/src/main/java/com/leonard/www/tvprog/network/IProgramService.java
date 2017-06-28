package com.leonard.www.tvprog.network;

import com.leonard.www.tvprog.model.Channels;
import com.leonard.www.tvprog.model.Programs;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by leoxw on 06/24/2017.
 */

public interface IProgramService {
    @GET("channel_list.json")
    Observable<Channels> getChannels();

    @GET("channel_programs_{channelId}.json")
    Observable<Programs> getPrograms(@Path("channelId")int channelId);
}
