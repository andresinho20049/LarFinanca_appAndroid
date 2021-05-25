
package com.andre.larfinancas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Financeiro {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
