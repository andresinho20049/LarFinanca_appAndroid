package com.andre.larfinancas.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andre.larfinancas.R;

public class ExtratoViewHolder extends RecyclerView.ViewHolder {

    public TextView desc;
    public TextView data;
    public TextView valor;
    public TextView tipo;

    public ExtratoViewHolder(@NonNull View itemView) {
        super(itemView);
        desc = itemView.findViewById(R.id.extrato_id_desc);
        data = itemView.findViewById(R.id.extrato_id_data);
        valor = itemView.findViewById(R.id.extrato_id_valor);
        tipo = itemView.findViewById(R.id.extrato_id_tipo);
    }
}
