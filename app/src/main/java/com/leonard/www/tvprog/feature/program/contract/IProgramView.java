package com.leonard.www.tvprog.feature.program.contract;

import com.leonard.www.tvprog.contract.IBaseView;
import com.leonard.www.tvprog.feature.program.model.ProgramsForView;

/**
 * Created by leoxw on 06/24/2017.
 */

public interface IProgramView extends IBaseView<ProgramsForView> {
    int getChannelId();
}
