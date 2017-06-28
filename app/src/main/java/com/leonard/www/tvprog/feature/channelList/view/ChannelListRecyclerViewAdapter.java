package com.leonard.www.tvprog.feature.channelList.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leonard.www.tvprog.R;
import com.leonard.www.tvprog.feature.program.view.ChannelDetailActivity;
import com.leonard.www.tvprog.feature.program.view.ChannelDetailFragment;
import com.leonard.www.tvprog.model.Channel;

import java.util.List;

/**
 * Created by leoxw on 06/24/2017.
 */
public class ChannelListRecyclerViewAdapter
        extends RecyclerView.Adapter<ChannelListRecyclerViewAdapter.ViewHolder> {
    private ChannelListActivity channelListActivity;
    private List<Channel> channelList;

    public ChannelListRecyclerViewAdapter(ChannelListActivity channelListActivity) {
        this.channelListActivity = channelListActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.channel = channelList.get(position);
        holder.channelName.setText(channelList.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (channelListActivity.isTwoPane()) {
                    channelListActivity.hideEmptyView();
                    Bundle arguments = new Bundle();
                    arguments.putInt(ChannelDetailFragment.CHANNEL_ID, holder.channel.getChannelId());
                    arguments.putString(ChannelDetailFragment.CHANNEL_NAME, holder.channel.getName());
                    ChannelDetailFragment fragment = new ChannelDetailFragment();
                    fragment.setArguments(arguments);
                    channelListActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.channel_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ChannelDetailActivity.class);
                    intent.putExtra(ChannelDetailFragment.CHANNEL_ID, holder.channel.getChannelId());
                    intent.putExtra(ChannelDetailFragment.CHANNEL_NAME, holder.channel.getName());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (channelList == null) ? 0 : channelList.size();
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;

        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView channelName;
        public Channel channel;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            channelName = (TextView) view.findViewById(R.id.channel_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + channelName.getText() + "'";
        }
    }
}
