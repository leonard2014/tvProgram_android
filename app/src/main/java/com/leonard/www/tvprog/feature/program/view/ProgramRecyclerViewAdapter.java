package com.leonard.www.tvprog.feature.program.view;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leonard.www.tvprog.R;
import com.leonard.www.tvprog.feature.program.model.ProgramForView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leoxw on 06/24/2017.
 */
public class ProgramRecyclerViewAdapter
        extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder> {
    private List<ProgramForView> program;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.program_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.program = program.get(position);
        holder.title.setText(holder.program.title);
        holder.startTime.setText(holder.program.localStartDate);
        holder.image.setImageURI(Uri.parse(holder.program.imageUrl));
    }

    @Override
    public int getItemCount() {
        return (program == null) ? 0 : program.size();
    }

    public void setProgram(List<ProgramForView> program) {
        this.program = program;

        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.start_time) TextView startTime;
        @BindView(R.id.programImage) SimpleDraweeView image;

        public ProgramForView program;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this,view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + program.title + "'";
        }
    }
}
