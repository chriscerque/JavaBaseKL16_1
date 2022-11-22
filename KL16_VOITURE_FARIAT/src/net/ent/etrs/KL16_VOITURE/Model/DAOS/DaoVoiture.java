package net.ent.etrs.KL16_VOITURE.Model.DAOS;

import net.ent.etrs.KL16_VOITURE.Model.DAOS.exceptions.DaoException;
import net.ent.etrs.KL16_VOITURE.Model.entities.Voiture;

import java.util.List;

public interface DaoVoiture {
    Voiture create(Voiture voiture) throws DaoException;

    Voiture read(final String libelle) throws DaoException;

    void delete(String libelle) throws DaoException;

    Voiture update(Voiture batiment) throws DaoException;

    List<Voiture> readAll();

    boolean exist(Voiture voiture) throws DaoException;



}
