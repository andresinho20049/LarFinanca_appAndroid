package com.andre.larfinancas.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.andre.larfinancas.models.Pagamento;

import java.util.List;

@Dao
public interface PagamentoDao {

    @Transaction
    @Query("Select * from Pagamento")
    LiveData<List<Pagamento>> findAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pagamento pagamento);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Pagamento pagamento);

    @Delete
    void delete(Pagamento pagamento);
}
