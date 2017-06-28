package com.leonard.www.tvprog.feature.program.mapper;

import com.leonard.www.tvprog.feature.program.model.ProgramForView;
import com.leonard.www.tvprog.model.Program;
import com.leonard.www.tvprog.model.Programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import easymvp.boundary.DataMapper;

/**
 * Created by leoxw on 06/24/2017.
 */

public class SortProgramListMapper extends DataMapper<Programs, List<ProgramForView>> {
    @Inject
    public SortProgramListMapper() {
    }

    @Override
    public List<ProgramForView> apply(Programs programs) throws Exception {
        List<Program> sorted = new LinkedList<>();
        sorted.addAll(programs.getPrograms());

        Collections.sort(sorted,
                (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));

        List<ProgramForView> listForView = new ArrayList<>();

        int i = 0;
        for(Program program : sorted) {
            listForView.add(i++, new ProgramForView(program));
        }

        return listForView;
    }
}
