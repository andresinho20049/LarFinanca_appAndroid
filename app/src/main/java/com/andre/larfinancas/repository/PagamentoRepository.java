package com.andre.larfinancas.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andre.larfinancas.dao.PagamentoDao;
import com.andre.larfinancas.models.Pagamento;

import java.util.List;

public class PagamentoRepository {

    private PagamentoDao pagamentoDao;
    private LiveData<List<Pagamento>> listFinanceiro;

    public PagamentoRepository(Application application){
        RoomDatabaseImp db = RoomDatabaseImp.getDatabase(application);
        pagamentoDao = db.pagamentoDao();
        listFinanceiro = pagamentoDao.findAll();
    }

    public LiveData<List<Pagamento>> getListFinanceiro() {
        return listFinanceiro;
    }

    public void insertPagamento(Pagamento pagamento){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            pagamentoDao.insert(pagamento);
        });
    }

    public void updatePagamento(Pagamento pagamento){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            pagamentoDao.update(pagamento);
        });
    }

    public void deletePagamento(Pagamento pagamento){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            pagamentoDao.delete(pagamento);
        });
    }

}
