package com.andre.larfinancas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;
import com.andre.larfinancas.repository.DespesaRepository;
import com.andre.larfinancas.repository.ReceitaRepository;

import java.util.List;

public class DespesaViewModel extends AndroidViewModel {

    private DespesaRepository despesaRepository;
    private final LiveData<List<Despesa>> allDespesa;

    public DespesaViewModel(@NonNull Application application) {
        super(application);
        despesaRepository = new DespesaRepository(application);
        allDespesa = despesaRepository.getListDespesa();
    }

    public LiveData<List<Despesa>> getAllFinanceiro(){ return allDespesa;}

    public void insert(Despesa despesa) { despesaRepository.insert(despesa);}

    public void insertWithPage(Despesa despesa, Pagamento pagamento) { despesaRepository.insertWithPage(despesa, pagamento);}

    public void update(Despesa despesa) { despesaRepository.update(despesa);}
}
