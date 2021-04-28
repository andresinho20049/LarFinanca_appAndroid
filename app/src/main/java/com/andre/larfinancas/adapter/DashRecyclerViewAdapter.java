package com.andre.larfinancas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andre.larfinancas.R;
import com.andre.larfinancas.viewholder.DashboardViewHolder;

public class DashRecyclerViewAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_dashboard,parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
