package com.andre.larfinancas.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andre.larfinancas.MainActivity;
import com.andre.larfinancas.NavigationHost;
import com.andre.larfinancas.R;
import com.andre.larfinancas.models.Carteira;
import com.andre.larfinancas.models.Conta;
import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.FinanceiroWithRelation;
import com.andre.larfinancas.models.MovimentacaoTipo;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;
import com.andre.larfinancas.viewmodel.DespesaViewModel;
import com.andre.larfinancas.viewmodel.FinanceiroViewModel;
import com.andre.larfinancas.viewmodel.PagamentoViewModel;
import com.andre.larfinancas.viewmodel.ReceitaViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DashFragment extends Fragment {

    private ReceitaViewModel receitaViewModel;
    private DespesaViewModel despesaViewModel;
    private PagamentoViewModel pagamentoViewModel;
    private FloatingActionButton fab, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    private boolean isFABOpen = false;

    private Float saldoInicial = 0f, totalReceita = 0f, totalDespesa = 0f, totalPago = 0f;
    private TextView saldo, receita, despesa, pago, pendentes, vencidas, emAberto, agendado, pagamentoProx, previsto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash, container, false);

        // Set up the toolbar
        ((MainActivity)getActivity()).setUpToolbar(view, R.id.dash_grid, R.id.dash_id_app_bar);

        MaterialButton extratoButton = view.findViewById(R.id.dash_btn_extrato);
        extratoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new ExtratoFragment(), false);
            }
        });


        this.referenciarCampos(view);
        pagamentoViewModel = new ViewModelProvider(this).get(PagamentoViewModel.class);
        pagamentoViewModel.getAllPagamentos().observe(((MainActivity) getActivity()), pagamentos -> {
            for(Pagamento p:pagamentos){
                if (p.getValor() != null && p.getTipo().equals(MovimentacaoTipo.Despesa.name()))
                    totalPago += p.getValor();
            }
            changeValor();
        });

        receitaViewModel = new ViewModelProvider(this).get(ReceitaViewModel.class);
        receitaViewModel.getAllFinanceiro().observe(((MainActivity) getActivity()), receitas -> {
            for(Receita p:receitas){
                if (p.getValor() != null)
                    totalReceita += p.getValor();
            }
            changeValor();
        });

        despesaViewModel = new ViewModelProvider(this).get(DespesaViewModel.class);
        despesaViewModel.getAllFinanceiro().observe(((MainActivity) getActivity()), despesas -> {
            for(Despesa p:despesas){
                if (p.getValor() != null)
                    totalDespesa += p.getValor();
            }
            changeValor();
        });



        MaterialButton painelButton = view.findViewById(R.id.backdrop_painel);
        painelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new DashFragment(), false);
            }
        });

        fabLayout1 = (LinearLayout) view.findViewById(R.id.fabLayout1);
        fabLayout2 = (LinearLayout) view.findViewById(R.id.fabLayout2);
        fabLayout3 = (LinearLayout) view.findViewById(R.id.fabLayout3);
        fabBGLayout = view.findViewById(R.id.fabBGLayout);
        fab = view.findViewById(R.id.fab);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new DespesaFragment(), false);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new ReceitaFragment(), false);
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

    private void showFABMenu(){
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-150);
        fabLayout2.animate().translationY(-300);
        fabLayout3.animate().translationY(-450);
    }

    private void closeFABMenu(){
        isFABOpen = false;
        fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0);
        fabLayout3.animate().translationY(0);
        fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                    fabLayout3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void referenciarCampos(View view){
        saldo = view.findViewById(R.id.dash_id_saldo);
        receita = view.findViewById(R.id.dash_id_receita);
        despesa = view.findViewById(R.id.dash_id_despesa);
        pago = view.findViewById(R.id.dash_id_pago);
        pendentes = view.findViewById(R.id.dash_id_pendente);
        vencidas = view.findViewById(R.id.dash_id_vencidas);
        emAberto = view.findViewById(R.id.dash_id_em_aberto);
        agendado = view.findViewById(R.id.dash_id_agendado);
        pagamentoProx = view.findViewById(R.id.dash_id_pgproximo);
        previsto = view.findViewById(R.id.dash_id_previsto);
    }

    private void changeValor() {
        Float valorSaldo, valorPendentes, valorPrevisto;

        valorSaldo = totalReceita - totalPago;
        valorPendentes = totalDespesa - totalPago;
        valorPrevisto = valorSaldo - valorPendentes;

        saldo.setText(formatNumber(valorSaldo));
        receita.setText(formatNumber(totalReceita));
        despesa.setText(formatNumber(totalDespesa));
        pago.setText(formatNumber(totalPago));
        pendentes.setText(formatNumber(valorPendentes));
        vencidas.setText(formatNumber(0));
        emAberto.setText(formatNumber(valorPendentes));
        agendado.setText(formatNumber(0));
        pagamentoProx.setText("28/04/2021");
        previsto.setText(formatNumber(valorPrevisto));
    }

    private String formatNumber(float value) {
        Locale locale = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(value);
    }
}