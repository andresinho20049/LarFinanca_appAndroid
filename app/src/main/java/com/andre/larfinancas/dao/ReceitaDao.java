package com.andre.larfinancas.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.andre.larfinancas.models.Financeiro;
import com.andre.larfinancas.models.Pagamento;
import com.andre.larfinancas.models.Receita;

import java.util.List;

@Dao
public interface ReceitaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Receita receita);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWithPag(Receita receita, Pagamento pagamento);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Receita receita);

    @Delete
    void delete(Receita receita);

    @Transaction
    @Query("select * from Receita")
    LiveData<List<Receita>> findAll();

}
