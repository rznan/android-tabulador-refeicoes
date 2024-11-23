package br.com.renan.trabalho_semestral.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;
import br.com.renan.trabalho_semestral.persistence.ICRUDDao;
import br.com.renan.trabalho_semestral.persistence.IOpenClosableDAO;

public abstract class GenericConsumivelController<T extends Consumivel> implements IController<T> {

    protected final IOpenClosableDAO<Consumivel, ICRUDDao<Consumivel>> DAO;

    protected abstract TipoConsumivel getConsumivelTipo();


    public GenericConsumivelController(IOpenClosableDAO<Consumivel, ICRUDDao<Consumivel>> dao) {
        DAO = dao;
        // facade no persistence que sabe contruir
    }

    @Override
    public void insert(T t) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.insert(t);
        DAO.close();
    }

    @Override
    public void update(T t) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.update(t);
        DAO.close();
    }

    @Override
    public void delete(T t) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.delete(t);
        DAO.close();
    }

    @Override
    public T search(T t) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        Consumivel one = DAO.findOne(t);
        DAO.close();
        if(one.getTipo() == getConsumivelTipo()) {
            //noinspection unchecked
            return (T) one;
        }
        throw new SQLException("Não foi possível encontrar o alimento");
    }

    @Override
    public List<T> list() throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        List<Consumivel> all = DAO.findByType(getConsumivelTipo().getValue());
        DAO.close();
        List<T> result = new ArrayList<>();
        for(Consumivel c : all) {
            //noinspection unchecked
            result.add((T) c);
        }
        return result;
    }
}
