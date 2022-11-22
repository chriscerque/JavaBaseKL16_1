package net.ent.etrs.gestionVoiture.model.daos;


import net.ent.etrs.gestionVoiture.model.daos.exceptions.DaoException;

import java.util.List;

public interface Dao<T, K> {
    T create(T t) throws DaoException;

    T read(K k) throws DaoException;

    List<T> readAll();

    T update(T t) throws DaoException;

    void delete(K k) throws DaoException;

    boolean exist(T t) throws DaoException;

    void init();
}
