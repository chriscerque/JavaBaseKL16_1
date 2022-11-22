package net.ent.etrs.model.daos;

import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.Voiture;

import java.util.List;

public interface DaoVoiture <T, K> {

    Voiture create(Voiture voiture) throws DaoException;

    Voiture read(String NumSerie) throws DaoException;

    void delete(String NumSerie) throws DaoException;

    Voiture update(Voiture voiture) throws DaoException;

    List<Voiture> readall();

    boolean exist(Voiture voiture) throws DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }
}
