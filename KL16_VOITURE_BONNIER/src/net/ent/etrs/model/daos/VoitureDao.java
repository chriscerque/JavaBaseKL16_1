package net.ent.etrs.model.daos;

import net.ent.etrs.model.daos.exceptions.DaoException;
import net.ent.etrs.model.entities.Voiture;

import java.util.List;

public interface VoitureDao {

    Voiture create(final Voiture voiture) throws DaoException;

    Voiture read(final String numSerie) throws DaoException;

    Voiture update(final Voiture voiture) throws DaoException;

    void delete(final String numSerie) throws DaoException;

    List<Voiture> readAll() throws DaoException;

    boolean exist(Voiture voiture) throws DaoException;


}
