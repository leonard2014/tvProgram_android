package com.leonard.www.tvprog.feature.channelList.presenter;

import com.leonard.www.tvprog.feature.channelList.contract.IChannelListView;
import com.leonard.www.tvprog.feature.channelList.domain.GetChannelListUseCase;
import com.leonard.www.tvprog.feature.channelList.mapper.SortChannelListMapper;
import com.leonard.www.tvprog.model.Channel;

import java.util.List;

import javax.inject.Inject;

import easymvp.RxPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by leoxw on 06/24/2017.
 */

public class ChannelListPresenter extends RxPresenter<IChannelListView> {
    protected GetChannelListUseCase getChannelListUseCase;
    protected SortChannelListMapper sortChannelListMapper;
    protected List<Channel> sortedChannelList;
    protected boolean isInProgress;

    @Inject
    public ChannelListPresenter(GetChannelListUseCase getChannelListUseCase, SortChannelListMapper sortChannelListMapper) {
        this.getChannelListUseCase = getChannelListUseCase;
        this.sortChannelListMapper = sortChannelListMapper;
    }

    public void presentSortedChannelList() {
        if(isInProgress) {
            return;
        }

        if(sortedChannelList != null) {
            getView().showContent(sortedChannelList);
            return;
        }

        isInProgress = true;
        getView().showProgress();

        addSubscription(
            getChannelListUseCase
                    .execute(null)
                    .map(sortChannelListMapper)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate(() -> {
                        isInProgress = false;
                        getView().hideProgress();
                    })
                    .subscribe(
                            sortedList -> {
                                sortedChannelList = sortedList;
                                getView().showContent(sortedChannelList);
                            },
                            error -> getView().showError()
                    )
                );
    }

    @Override
    public void onViewAttached(IChannelListView view) {
        super.onViewAttached(view);

        if(isInProgress) {
            view.showProgress();
        } else {
            presentSortedChannelList();
        }
    }
}
