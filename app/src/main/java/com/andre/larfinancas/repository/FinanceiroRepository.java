package com.andre.larfinancas.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andre.larfinancas.dao.FinanceiroDao;
import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.FinanceiroWithRelation;

import java.util.List;

public class FinanceiroRepository {

    private FinanceiroDao financeiroDao;
    private LiveData<List<FinanceiroWithRelation>> listFinanceiro;

    public FinanceiroRepository(Application application){
        RoomDatabaseImp db = RoomDatabaseImp.getDatabase(application);
        financeiroDao = db.financeiroDao();
        listFinanceiro = financeiroDao.findAll();
    }

    public LiveData<List<FinanceiroWithRelation>> getListFinanceiro() {
        return listFinanceiro;
    }

    public void insert(Financeiro financeiro){
        RoomDatabaseImp.databaseWriteExecutor.execute(() -> {
            financeiroDao.insert(financeiro);
        });
    }
}
