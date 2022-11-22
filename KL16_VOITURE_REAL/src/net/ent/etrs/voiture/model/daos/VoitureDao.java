package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.List;

public interface VoitureDao {
    Voiture create(final Voiture voiture) throws DaoException;

    Voiture read(final String numSerie) throws DaoException;

    void delete(final String numSerie) throws DaoException;

    Voiture update(final Voiture voiture) throws DaoException;

    List<Voiture> readAll() throws DaoException;
}
