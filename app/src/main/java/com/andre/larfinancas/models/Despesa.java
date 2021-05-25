
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Despesa {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String descricao;
    private String competencia;
    private String vencimento;
    private Boolean parcelado;
    private Float valor;
    private Long userId;

    public Despesa(String descricao, String competencia, String vencimento, Boolean parcelado, Float valor) {
        this.descricao = descricao;
        this.competencia = competencia;
        this.vencimento = vencimento;
        this.parcelado = parcelado;
        this.valor = valor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public Boolean getParcelado() {
        return parcelado;
    }

    public void setParcelado(Boolean parcelado) {
        this.parcelado = parcelado;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}