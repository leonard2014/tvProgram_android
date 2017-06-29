package com.leonard.www.tvprog.feature.program.model;

import java.util.List;

/**
 * Created by Leonard Wu on 30/6/17.
 */

public class ProgramsForView {
    public final int channelID;
    public final List<ProgramForView> programs;

    public ProgramsForView(int channelID, List<ProgramForView> programs) {
        this.channelID = channelID;
        this.programs = programs;
    }
}
