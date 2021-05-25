package com.andre.larfinancas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.andre.larfinancas.R;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.viewholder.ExtratoViewHolder;

import java.text.NumberFormat;
import java.util.Locale;


public class ExtratoRecyclerViewAdapter extends ListAdapter<Pagamento, ExtratoViewHolder> {

    public ExtratoRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Pagamento> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ExtratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_extrato, parent, false);
        return new ExtratoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtratoViewHolder holder, int position) {

        Pagamento pagamento = getItem(position);

        holder.desc.setText(pagamento.getDesc());
        holder.data.setText(pagamento.getData());
        holder.tipo.setText(pagamento.getTipo());
        holder.valor.setText(formatNumber(pagamento.getValor()));
    }


    public static class FinanceiroDiff extends DiffUtil.ItemCallback<Pagamento> {

        @Override
        public boolean areItemsTheSame(@NonNull Pagamento oldItem, @NonNull Pagamento newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pagamento oldItem, @NonNull Pagamento newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

    private String formatNumber(float value) {
        Locale locale = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(value);
    }
}
