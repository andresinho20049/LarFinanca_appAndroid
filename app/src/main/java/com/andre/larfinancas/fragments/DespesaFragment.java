package com.andre.larfinancas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andre.larfinancas.MainActivity;
import com.andre.larfinancas.NavigationHost;
import com.andre.larfinancas.R;
import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.MovimentacaoTipo;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;
import com.andre.larfinancas.viewmodel.DespesaViewModel;
import com.andre.larfinancas.viewmodel.ReceitaViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DespesaFragment extends Fragment {

    private DespesaViewModel despesaViewModel;

    EditText descE;
    EditText compE;
    EditText dataE;
    EditText valorE;
    EditText valorPE;
    EditText dataPE;
    Switch pago;
    String userId;
    Boolean pagamentoEfetuado = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_despesa, container, false);

        // Set up the toolbar
        ((MainActivity)getActivity()).setUpToolbar(view, R.id.receita_grid, R.id.receita_id_app_bar);

        MaterialButton painelButton = view.findViewById(R.id.backdrop_painel);
        painelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new DashFragment(), false);
            }
        });

        despesaViewModel = new ViewModelProvider(this).get(DespesaViewModel.class);

        userId = ((MainActivity) getActivity()).getUserId();
        descE = view.findViewById(R.id.receita_id_desc);
        compE = view.findViewById(R.id.receita_id_comp);
        dataE = view.findViewById(R.id.receita_id_data);
        valorE = view.findViewById(R.id.receita_id_valor);
        valorPE = view.findViewById(R.id.receita_id_vpagamento);
        dataPE = view.findViewById(R.id.receita_id_pagamento);
        MaterialButton salve = view.findViewById(R.id.receita_id_btnSalve);
        salve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = descE.getText().toString();
                String data = dataE.getText().toString();
                String comp = compE.getText().toString();
                String value = valorE.getText().toString();
                String valuePg = valorPE.getText().toString();

                String dataPg = dataPE.getText().toString();

                if (descricao == null || descricao.isEmpty() || data == null || data.isEmpty()
                        || comp == null || comp.isEmpty() || value == null || value.isEmpty()){
                    Toast.makeText(getContext(), "Favor Preencher todos os campos", Toast.LENGTH_SHORT).show();
                } else if(pagamentoEfetuado && (valuePg == null || valuePg.isEmpty() || dataPg == null || dataPg.isEmpty())) {
                    Toast.makeText(getContext(), "Favor informar os dados do Pagamento", Toast.LENGTH_SHORT).show();
                } else {
                    Float valor = (Float) Float.parseFloat(value);
                    Despesa despesa = new Despesa(descricao, comp, data, false, valor);

                    if (pagamentoEfetuado) {
                        Float valorPg = (Float) Float.parseFloat(valuePg);
                        Pagamento pagamento = new Pagamento(descricao, valorPg, MovimentacaoTipo.Despesa.name(), dataPg);
                        despesaViewModel.insertWithPage(despesa, pagamento);
                    } else {
                        despesaViewModel.insert(despesa);
                    }

                    Toast.makeText(getContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    ((NavigationHost) getActivity()).navigateTo(new ExtratoFragment(), false);
                }
            }
        });


        pago = view.findViewById(R.id.receita_id_switch);
        pago.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    valorPE.setVisibility(View.VISIBLE);
                    dataPE.setVisibility(View.VISIBLE);
                    valorPE.setText(valorE.getText());
                    pagamentoEfetuado = true;
                } else {
                    valorPE.setVisibility(View.GONE);
                    dataPE.setVisibility(View.GONE);
                    pagamentoEfetuado = false;
                }
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                ((MainActivity)getActivity()).signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}