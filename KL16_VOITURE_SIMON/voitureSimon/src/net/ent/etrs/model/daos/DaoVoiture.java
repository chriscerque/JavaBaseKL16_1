package net.ent.etrs.model.daos;


import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.Voiture;

import java.util.List;

/**
 * The interface Dao voiture.
 */
public interface DaoVoiture {

    Voiture create(Voiture voiture) throws DaoException;
    Voiture read(String numSerie) throws DaoException;

    void delete(String numSerie) throws DaoException;

    Voiture update(Voiture voiture) throws DaoException;

    List<Voiture> readAll();

    boolean exist(Voiture voiture) throws DaoException;



}
