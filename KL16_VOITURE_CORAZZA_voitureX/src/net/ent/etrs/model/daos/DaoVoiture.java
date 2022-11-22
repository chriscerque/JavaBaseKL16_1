package net.ent.etrs.model.daos;

import net.ent.etrs.model.entities.Voiture;
import net.ent.etrs.voiture.model.daos.exceptions.DaoException;

import java.util.List;

public interface DaoVoiture {

    Voiture create(Voiture voiture) throws DaoException;

    Voiture read(String libelle) throws DaoException;

    void delete(String libelle) throws DaoException;

    Voiture update(Voiture voiture) throws DaoException;

    List<Voiture> readAll();

    default void init() {
        throw new UnsupportedOperationException();
    }

    boolean exist(Voiture voiture) throws DaoException;
}
