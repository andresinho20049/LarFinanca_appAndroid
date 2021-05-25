package com.andre.larfinancas.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FinanceiroWithRelation {

    @Embedded
    Financeiro financeiro;

    @Relation(parentColumn = "id", entityColumn = "financeiroId", entity = Conta.class)
    List<Conta> contas;

    @Relation(parentColumn = "id", entityColumn = "financeiroId", entity = Carteira.class)
    Carteira carteira;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Receita.class)
    List<Receita> receitas;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Despesa.class)
    List<Despesa> despesas;

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }
}
