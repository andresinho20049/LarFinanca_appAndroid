
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class CentroCusto {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long fkId;
    private String nome;
    private String obs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkId() {
        return fkId;
    }

    public void setFkId(Long fkId) {
        this.fkId = fkId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
