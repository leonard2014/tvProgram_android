package com.leonard.www.tvprog.feature.program.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gturedi.views.StatefulLayout;
import com.leonard.www.tvprog.R;
import com.leonard.www.tvprog.app.MyApplication;
import com.leonard.www.tvprog.feature.channelList.view.ChannelListActivity;
import com.leonard.www.tvprog.feature.program.contract.IProgramView;
import com.leonard.www.tvprog.feature.program.model.ProgramForView;
import com.leonard.www.tvprog.feature.program.presenter.ProgramPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import easymvp.annotation.FragmentView;
import easymvp.annotation.Presenter;

/**
 * A fragment representing a single Channel detail screen.
 * This fragment is either contained in a {@link ChannelListActivity}
 * in two-pane mode (on tablets) or a {@link ChannelDetailActivity}
 * on handsets.
 */
@FragmentView(presenter = ProgramPresenter.class)
public class ChannelDetailFragment extends Fragment implements IProgramView {
    @Inject
    @Presenter
    ProgramPresenter presenter;

    @BindView(R.id.program)
    RecyclerView programView;
    @BindView(R.id.statefulProgram)
    StatefulLayout programStateful;

    ProgramRecyclerViewAdapter adapter;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String CHANNEL_ID = "channel_id";
    public static final String CHANNEL_NAME = "channel_name";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChannelDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MyApplication)getActivity().getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CHANNEL_NAME)) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getArguments().getString(CHANNEL_NAME));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.channel_detail, container, false);

        ButterKnife.bind(this, rootView);

        setupRecyclerView();

        return rootView;
    }

    private void setupRecyclerView() {
        adapter = new ProgramRecyclerViewAdapter();
        programView.setAdapter(adapter);
        programView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showProgress() {
        programStateful.showLoading();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {
        programStateful.showError(R.string.could_not_get_channel_list, v -> presenter.presentSortedProgram());
    }

    @Override
    public void showContent(List<ProgramForView> programs) {
        programStateful.showContent();
        adapter.setProgram(programs);
    }

    @Override
    public int getChannelId() {
        if (getArguments().containsKey(CHANNEL_ID)) {
            return getArguments().getInt(CHANNEL_ID);
        } else {
            return -1;
        }
    }
}
