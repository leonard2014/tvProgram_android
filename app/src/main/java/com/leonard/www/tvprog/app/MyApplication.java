package com.leonard.www.tvprog.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.leonard.www.tvprog.di.AppComponent;
import com.leonard.www.tvprog.di.DaggerAppComponent;
import com.leonard.www.tvprog.di.NetModule;
import com.leonard.www.tvprog.feature.channelList.di.ChannelListModule;
import com.leonard.www.tvprog.feature.program.di.ProgramModule;
import com.leonard.www.tvprog.network.ServiceUrl;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by leoxw on 06/24/2017.
 */

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent =
            DaggerAppComponent
                    .builder()
                    .netModule(new NetModule(ServiceUrl.ProgramUrl))
                    .channelListModule(new ChannelListModule(() -> Schedulers.io(), () -> Schedulers.computation()))
                    .programModule(new ProgramModule(() -> Schedulers.io(), () -> Schedulers.computation()))
                    .build();

        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
