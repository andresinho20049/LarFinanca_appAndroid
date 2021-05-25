package com.andre.larfinancas.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andre.larfinancas.dao.FinanceiroDao;
import com.andre.larfinancas.dao.ReceitaDao;
import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;

import java.util.List;

public class ReceitaRepository {

    private ReceitaDao receitaDao;
    private LiveData<List<Receita>> listReceita;

    public ReceitaRepository(Application application){
        RoomDatabaseImp db = RoomDatabaseImp.getDatabase(application);
        receitaDao = db.receitaDao();
        listReceita = receitaDao.findAll();
    }

    public LiveData<List<Receita>> getListFinanceiro() {
        return listReceita;
    }

    public void insert(Receita receita){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
           receitaDao.insert(receita);
        });
    }

    public void inserWithPag(Receita receita, Pagamento pagamento){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            receitaDao.insertWithPag(receita, pagamento);
        });
    }

    public void update(Receita receita){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            receitaDao.update(receita);
        });
    }

    public void delete(Receita receita){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            receitaDao.delete(receita);
        });
    }
}
