package com.andre.larfinancas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.repository.PagamentoRepository;

import java.util.List;

public class PagamentoViewModel extends AndroidViewModel {

    private PagamentoRepository pagamentoRepository;
    private final LiveData<List<Pagamento>> pagamentos;

    public PagamentoViewModel(@NonNull Application application) {
        super(application);
        pagamentoRepository = new PagamentoRepository(application);
        pagamentos = pagamentoRepository.getListFinanceiro();
    }

    public LiveData<List<Pagamento>> getAllPagamentos(){return pagamentos;}

    public void insertPagamento(Pagamento pagamento){pagamentoRepository.insertPagamento(pagamento);}

    public void updatePagamento(Pagamento pagamento){pagamentoRepository.updatePagamento(pagamento);}

    public void deletePagamento(Pagamento pagamento){pagamentoRepository.deletePagamento(pagamento);}
}
