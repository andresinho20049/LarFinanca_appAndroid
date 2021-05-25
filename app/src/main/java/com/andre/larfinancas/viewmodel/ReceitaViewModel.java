package com.andre.larfinancas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;
import com.andre.larfinancas.repository.FinanceiroRepository;
import com.andre.larfinancas.repository.ReceitaRepository;

import java.util.List;

public class ReceitaViewModel extends AndroidViewModel {

    private ReceitaRepository receitaRepository;
    private final LiveData<List<Receita>> allReceita;

    public ReceitaViewModel(@NonNull Application application) {
        super(application);
        receitaRepository = new ReceitaRepository(application);
        allReceita = receitaRepository.getListFinanceiro();
    }

    public LiveData<List<Receita>> getAllFinanceiro(){ return allReceita;}

    public void insert(Receita receita) { receitaRepository.insert(receita);}

    public void update(Receita receita) { receitaRepository.update(receita);}

    public void insertWithPag(Receita receita, Pagamento pagamento) {receitaRepository.inserWithPag(receita, pagamento);}
}
