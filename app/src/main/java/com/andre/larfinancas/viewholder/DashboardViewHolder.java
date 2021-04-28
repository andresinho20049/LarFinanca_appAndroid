package com.andre.larfinancas.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andre.larfinancas.R;

public class DashboardViewHolder extends RecyclerView.ViewHolder {

    public TextView saldo;
    public TextView receita;
    public TextView despesa;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        saldo = itemView.findViewById(R.id.dash_id_saldo);
        receita = itemView.findViewById(R.id.dash_id_receita);
        despesa = itemView.findViewById(R.id.dash_id_despesa);
    }
}
