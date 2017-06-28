package com.leonard.www.tvprog.feature.program.model;

import com.leonard.www.tvprog.model.Program;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by leoxw on 06/26/2017.
 */

public class ProgramForView {
    public final String imageUrl;
    public final String title;
    public final String localStartDate;

    private static Format formatter = new SimpleDateFormat("EEEE MMM d HH:mm z");

    public ProgramForView(Program program) {
        imageUrl = program.getImageUrl();
        title = program.getTitle();
        localStartDate = formatter.format(program.getStartTime());
    }
}
