package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.List;

public interface DaoVoiture {

    Voiture create(final Voiture voiture) throws net.ent.etrs.model.entities.exceptions.VoitureException, DaoException;

    Voiture read(final String numSerie) throws DaoException;

    Voiture update(final Voiture voiture) throws DaoException;

    void delete(final String numSerie) throws DaoException;

    boolean exist(final Voiture voiture) throws DaoException;

    List<Voiture> readAll();

    void init();
}
