
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Carteira {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long financeiroId;
    private Float saldoInicial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinanceiroId() {
        return financeiroId;
    }

    public void setFinanceiroId(Long financeiroId) {
        this.financeiroId = financeiroId;
    }

    public Float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}
