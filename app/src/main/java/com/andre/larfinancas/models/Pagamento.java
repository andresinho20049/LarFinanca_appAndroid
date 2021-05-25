package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Receita.class,
        parentColumns = "id",
        childColumns = "receitaId"),
        @ForeignKey(entity = Despesa.class,
        parentColumns = "id",
        childColumns = "despesaId")
})
public class Pagamento {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String desc;
    private Float valor;
    private String tipo;
    private String data;

    @ForeignKey(parentColumns = "id", childColumns = "receitaId", entity = Receita.class)
    private Long  receitaId;

    @ForeignKey(parentColumns = "id", childColumns = "despesaId", entity = Despesa.class)
    private Long  despesaId;

    public Pagamento(String desc, Float valor, String tipo, String data) {
        this.desc = desc;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(Long receitaId) {
        this.receitaId = receitaId;
    }

    public Long getDespesaId() {
        return despesaId;
    }

    public void setDespesaId(Long despesaId) {
        this.despesaId = despesaId;
    }
}
