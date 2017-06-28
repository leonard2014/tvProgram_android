package com.leonard.www.tvprog.feature.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leonard.www.tvprog.app.MyApplication;
import com.leonard.www.tvprog.di.AppComponent;

/**
 * Created by leoxw on 06/24/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependency(((MyApplication)getApplication()).getAppComponent());
        super.onCreate(savedInstanceState);
    }

    protected abstract void injectDependency(AppComponent appComponent);
}
