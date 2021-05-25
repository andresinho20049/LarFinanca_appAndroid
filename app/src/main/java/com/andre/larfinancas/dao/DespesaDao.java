package com.andre.larfinancas.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.andre.larfinancas.models.Despesa;
import com.andre.larfinancas.models.Pagamento;

import java.util.List;

@Dao
public interface DespesaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Despesa despesa);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWithPagamento(Despesa despesa, Pagamento pagamento);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Despesa despesa);

    @Delete
    void delete(Despesa despesa);

    @Transaction
    @Query("select * from Despesa")
    LiveData<List<Despesa>> findAll();

}
