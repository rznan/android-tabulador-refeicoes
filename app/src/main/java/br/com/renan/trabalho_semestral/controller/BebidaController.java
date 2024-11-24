package br.com.renan.trabalho_semestral.controller;

import br.com.renan.trabalho_semestral.model.Bebida;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;
import br.com.renan.trabalho_semestral.persistence.ConsumivelDao;

public class BebidaController extends GenericConsumivelController<Bebida> {

    public BebidaController(ConsumivelDao dao) {
        super(dao);
    }

    @Override
    protected TipoConsumivel getConsumivelTipo() {
        return TipoConsumivel.BEBIDA;
    }
}
