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
import com.andre.larfinancas.models.FinanceiroWithRelation;

import java.util.List;

@Dao
public interface FinanceiroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Financeiro financeiro);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Financeiro financeiro);

    @Delete
    void delete(Financeiro financeiro);

    @Transaction
    @Query("select * from financeiro")
    LiveData<List<FinanceiroWithRelation>> findAll();

}
