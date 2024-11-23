package br.com.renan.trabalho_semestral.controller;

import java.sql.SQLException;
import java.util.List;

/**
 *@author: renan santos carvalho
 */
public interface IController<T> {
    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;

    T search(T t) throws SQLException;

    List<T> list() throws SQLException;
}
