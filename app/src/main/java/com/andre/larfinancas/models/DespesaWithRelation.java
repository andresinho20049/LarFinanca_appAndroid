package com.andre.larfinancas.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DespesaWithRelation {

    @Embedded
    Despesa despesa;

    @Relation(parentColumn = "id", entityColumn = "fkId", entity = CentroCusto.class)
    List<CentroCusto> centroCustos;

    @Relation(parentColumn = "id", entityColumn = "fkId", entity = Pagamento.class)
    List<Pagamento> pagamentos;
}
