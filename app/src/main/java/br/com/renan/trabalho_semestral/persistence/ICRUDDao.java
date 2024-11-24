package br.com.renan.trabalho_semestral.persistence;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: renan santos carvalho
 */
public interface ICRUDDao<T> {

    void insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    void delete(T t) throws SQLException;

    T findOne(T t) throws SQLException;

    List<T> findAll() throws SQLException;

    List<T> findByType(int typeCode) throws SQLException;
}
