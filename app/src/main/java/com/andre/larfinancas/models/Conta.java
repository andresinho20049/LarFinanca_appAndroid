
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Conta {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long financeiroId;
    private String banco;
    private String conta;
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

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}
