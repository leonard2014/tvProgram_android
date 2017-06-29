package com.leonard.www.tvprog.feature.program.presenter;

import com.leonard.www.tvprog.feature.program.contract.IProgramView;
import com.leonard.www.tvprog.feature.program.domain.GetProgramUseCase;
import com.leonard.www.tvprog.feature.program.mapper.SortProgramListMapper;
import com.leonard.www.tvprog.feature.program.model.ProgramsForView;

import javax.inject.Inject;

import easymvp.RxPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by leoxw on 06/24/2017.
 */

public class ProgramPresenter extends RxPresenter<IProgramView> {
    protected GetProgramUseCase getProgramUseCase;
    protected SortProgramListMapper sortProgramListMapper;
    protected ProgramsForView sortedPrograms;

    @Inject
    public ProgramPresenter(GetProgramUseCase getProgramUseCase, SortProgramListMapper sortProgramListMapper) {
        this.getProgramUseCase = getProgramUseCase;
        this.sortProgramListMapper = sortProgramListMapper;
    }

    public void presentSortedProgram(int channelID) {
        if(channelID < 0) {
            getView().showEmpty();
            return;
        }

        if(sortedPrograms != null && (sortedPrograms.channelID == channelID)) {
            getView().showContent(sortedPrograms);
            return;
        }

        getView().showProgress();

        addSubscription(
                getProgramUseCase
                        .execute(getView().getChannelId())
                        .map(sortProgramListMapper)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(() -> {
                              getView().hideProgress();
                        })
                        .subscribe(
                                sorted -> {
                                    sortedPrograms = sorted;
                                    getView().showContent(sortedPrograms);
                                },
                                error -> getView().showError()
                        )
        );
    }

    @Override
    public void onViewAttached(IProgramView view) {
        super.onViewAttached(view);

        presentSortedProgram(getView().getChannelId());
    }
}
