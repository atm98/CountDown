package com.agnt45.countdown.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agnt45.countdown.Classes.Events;
import com.agnt45.countdown.R;

import java.util.List;

/**
 * Created by an160 on 07-05-2018.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private List<Events> eventsList;
    @NonNull
    @Override
    public EventsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ename,edatetime,elocation;
        private ImageView epic;
        public MyViewHolder(View itemView) {
            super(itemView);
            ename = itemView.findViewById(R.id.eventname);
            edatetime = itemView.findViewById(R.id.eventdatetime);
            elocation = itemView.findViewById(R.id.eventloaction);
            epic = itemView.findViewById(R.id.eventpicture);
        }
    }
}
