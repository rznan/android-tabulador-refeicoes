package br.com.renan.trabalho_semestral.controller;

import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;
import br.com.renan.trabalho_semestral.persistence.ICRUDDao;
import br.com.renan.trabalho_semestral.persistence.IOpenClosableDAO;

public class AlimentoController extends GenericConsumivelController<Alimento> {

    public AlimentoController(IOpenClosableDAO<Consumivel, ICRUDDao<Consumivel>> dao) {
        // todo: use facade
        super(dao);
    }

    @Override
    protected TipoConsumivel getConsumivelTipo() {
        return TipoConsumivel.ALIMENTO;
    }
}
