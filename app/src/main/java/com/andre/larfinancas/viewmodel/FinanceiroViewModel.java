package com.andre.larfinancas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andre.larfinancas.dao.FinanceiroDao;
import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.FinanceiroWithRelation;
import com.andre.larfinancas.repository.FinanceiroRepository;

import java.util.List;

public class FinanceiroViewModel extends AndroidViewModel {

    private FinanceiroRepository financeiroRepository;
    private final LiveData<List<FinanceiroWithRelation>> allFinanceiro;

    public FinanceiroViewModel(@NonNull Application application) {
        super(application);
        financeiroRepository = new FinanceiroRepository(application);
        allFinanceiro = financeiroRepository.getListFinanceiro();
    }

    public LiveData<List<FinanceiroWithRelation>> getAllFinanceiro(){ return allFinanceiro;}

    public void insert(Financeiro financeiro) {financeiroRepository.insert(financeiro);}
}
