package br.com.renan.trabalho_semestral.persistence;

import java.sql.SQLException;

/**
 * @author: renan santos carvalho
 */
public interface IOpenClosableDAO<T, J extends ICRUDDao<T>> extends ICRUDDao<T> {
    J open() throws SQLException;

    void close() throws SQLException;
}