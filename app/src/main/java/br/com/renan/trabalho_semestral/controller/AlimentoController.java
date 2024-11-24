package br.com.renan.trabalho_semestral.controller;

import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;
import br.com.renan.trabalho_semestral.persistence.ConsumivelDao;

public class AlimentoController extends GenericConsumivelController<Alimento> {

    public AlimentoController(ConsumivelDao dao) {
        super(dao);
    }

    @Override
    protected TipoConsumivel getConsumivelTipo() {
        return TipoConsumivel.ALIMENTO;
    }
}
