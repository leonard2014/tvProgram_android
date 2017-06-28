package com.leonard.www.tvprog.feature.channelList.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gturedi.views.StatefulLayout;
import com.leonard.www.tvprog.R;
import com.leonard.www.tvprog.di.AppComponent;
import com.leonard.www.tvprog.feature.base.BaseActivity;
import com.leonard.www.tvprog.feature.channelList.contract.IChannelListView;
import com.leonard.www.tvprog.feature.channelList.presenter.ChannelListPresenter;
import com.leonard.www.tvprog.feature.program.view.ChannelDetailActivity;
import com.leonard.www.tvprog.model.Channel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import easymvp.annotation.ActivityView;
import easymvp.annotation.Presenter;

/**
 * An activity representing a list of Channels. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ChannelDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@ActivityView(layout = R.layout.activity_channel_list, presenter = ChannelListPresenter.class)
public class ChannelListActivity extends BaseActivity implements IChannelListView{
    @BindView(R.id.channel_list) RecyclerView channelListView;
    @BindView(R.id.statefulChannelList) StatefulLayout channelListStateful;
    @Nullable @BindView(R.id.empty_imageView) View emptyImageView;

    @Inject
    @Presenter
    ChannelListPresenter channelListPresenter;
    private ChannelListRecyclerViewAdapter adapter;

    public boolean isTwoPane() {
        return mTwoPane;
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setupToolbar();

        setupRecyclerView();

        if (findViewById(R.id.channel_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void injectDependency(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void setupRecyclerView() {
        adapter = new ChannelListRecyclerViewAdapter(this);
        channelListView.setAdapter(adapter);
        channelListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    public void showEmpty() {
        channelListStateful.showEmpty();
    }

    @Override
    public void showProgress() {
        channelListStateful.showLoading();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {
        channelListStateful.showError(R.string.could_not_get_channel_list, v -> channelListPresenter.presentSortedChannelList());
    }

    @Override
    public void showContent(List<Channel> channelList) {
        channelListStateful.showContent();
        adapter.setChannelList(channelList);
    }

    public void hideEmptyView() {
        if(emptyImageView != null) {
            emptyImageView.setVisibility(View.GONE);
        }
    }
}
