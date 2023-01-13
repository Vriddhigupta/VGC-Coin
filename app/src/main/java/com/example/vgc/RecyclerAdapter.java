package com.example.vgc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginclient.EventResponse;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.BeneficiaryViewHolder>{


    private ArrayList<EventResponse> listEvent;
    public ImageView logo;
    private Context mContext;

    public RecyclerViewClickListener listener;


    public RecyclerAdapter(ArrayList<EventResponse> listEvent, Context mContext, RecyclerViewClickListener listener) {
        this.listEvent = listEvent;
        this.mContext = mContext;
        this.listener = listener;
    }

    public class BeneficiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public TextView textViewDateTime;
        public TextView textViewDescription;
        public ImageView logo;

        public BeneficiaryViewHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.event_title);
            textViewDateTime= (TextView) view.findViewById(R.id.datetime);
            textViewDescription = (TextView) view.findViewById(R.id.description);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public BeneficiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new BeneficiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BeneficiaryViewHolder holder, int position) {
        holder.textViewName.setText(listEvent.get(position).getEventName());
        holder.textViewDateTime.setText(listEvent.get(position).getEventDate());
        holder.textViewDescription.setText(listEvent.get(position).getEventDescription());

    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }


    public interface RecyclerViewClickListener{
        void onClick(View v,int position);

    }
}