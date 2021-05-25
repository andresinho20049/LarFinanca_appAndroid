package com.andre.larfinancas.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andre.larfinancas.dao.DespesaDao;
import com.andre.larfinancas.dao.ReceitaDao;
import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;

import java.util.List;

public class DespesaRepository {

    private DespesaDao despesaDao;
    private LiveData<List<Despesa>> listDespesa;

    public DespesaRepository(Application application){
        RoomDatabaseImp db = RoomDatabaseImp.getDatabase(application);
        despesaDao = db.despesaDao();
        listDespesa = despesaDao.findAll();
    }

    public LiveData<List<Despesa>> getListDespesa() {
        return listDespesa;
    }

    public void insert(Despesa despesa){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            despesaDao.insert(despesa);
        });
    }

    public void insertWithPage(Despesa despesa, Pagamento pagamento){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            despesaDao.insertWithPagamento(despesa, pagamento);
        });
    }

    public void update(Despesa despesa){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            despesaDao.update(despesa);
        });
    }

    public void delete(Despesa despesa){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            despesaDao.delete(despesa);
        });
    }
}
