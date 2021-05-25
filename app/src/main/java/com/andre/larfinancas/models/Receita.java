
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Receita {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String competencia;
    private String dataPrevista;
    private String descricao;
    private Float valor;
    private String userId;

    public Receita(String competencia, String dataPrevista, String descricao, Float valor, String userId) {
        this.competencia = competencia;
        this.dataPrevista = dataPrevista;
        this.descricao = descricao;
        this.valor = valor;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
