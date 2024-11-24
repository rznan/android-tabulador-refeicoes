package br.com.renan.trabalho_semestral.controller;

import br.com.renan.trabalho_semestral.model.Bebida;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;
import br.com.renan.trabalho_semestral.persistence.ConsumivelDao;
import br.com.renan.trabalho_semestral.persistence.ICRUDDao;
import br.com.renan.trabalho_semestral.persistence.IOpenClosableDAO;

public class BebidaController extends GenericConsumivelController<Bebida> {

    public BebidaController(ConsumivelDao dao) {
        super(dao);
    }

    @Override
    protected TipoConsumivel getConsumivelTipo() {
        return TipoConsumivel.BEBIDA;
    }
}
