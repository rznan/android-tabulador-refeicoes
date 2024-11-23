package br.com.renan.trabalho_semestral.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.persistence.ICRUDDao;
import br.com.renan.trabalho_semestral.persistence.IOpenClosableDAO;

public class RefeicaoController implements IController<Refeicao> {

    final IOpenClosableDAO<Refeicao, ICRUDDao<Refeicao>> DAO;

    public RefeicaoController(IOpenClosableDAO<Refeicao, ICRUDDao<Refeicao>> dao) {
        DAO = dao;
    }

    @Override
    public void insert(Refeicao refeicao) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.insert(refeicao);
        DAO.close();
    }

    @Override
    public void update(Refeicao refeicao) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.update(refeicao);
        DAO.close();
    }

    @Override
    public void delete(Refeicao refeicao) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        DAO.delete(refeicao);
        DAO.close();
    }

    @Override
    public Refeicao search(Refeicao refeicao) throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        Refeicao one = DAO.findOne(refeicao);
        DAO.close();
        return one;
    }

    @Override
    public List<Refeicao> list() throws SQLException {
        if (DAO.open() == null) {
            DAO.open();
        }
        List<Refeicao> all = DAO.findAll();
        DAO.close();
        return all;
    }
}
