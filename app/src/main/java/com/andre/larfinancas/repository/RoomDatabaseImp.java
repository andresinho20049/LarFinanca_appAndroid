package com.andre.larfinancas.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andre.larfinancas.dao.DespesaDao;
import com.andre.larfinancas.dao.FinanceiroDao;
import com.andre.larfinancas.dao.PagamentoDao;
import com.andre.larfinancas.dao.ReceitaDao;
import com.andre.larfinancas.models.Carteira;
import com.andre.larfinancas.models.CentroCusto;
import com.andre.larfinancas.models.Conta;
import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Financeiro.class,
        Carteira.class,
        CentroCusto.class,
        Conta.class,
        Despesa.class,
        Pagamento.class,
        Receita.class},
version = 1, exportSchema = false)
public abstract class RoomDatabaseImp extends RoomDatabase {
    public abstract FinanceiroDao financeiroDao();
    public abstract PagamentoDao pagamentoDao();
    public abstract ReceitaDao receitaDao();
    public abstract DespesaDao despesaDao();

    private static volatile RoomDatabaseImp INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDatabaseImp getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (RoomDatabaseImp.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabaseImp.class, "financeiro_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
