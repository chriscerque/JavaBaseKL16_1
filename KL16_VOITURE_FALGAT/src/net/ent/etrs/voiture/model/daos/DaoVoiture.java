package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Voiture;

import java.util.List;

public interface DaoVoiture {

    /**
     * Création d'une voiture dans la DAO
     * @param voiture
     * @return La voiture crée
     * @throws DaoException
     */
    Voiture create(final Voiture voiture) throws DaoException;

    /**
     * Lecture d'une voiture en fonction du numéro de série
     * @param numSerie
     * @return La voiture correspondante
     * @throws DaoException
     */
    Voiture read(final String numSerie) throws DaoException;

    /**
     * Suppression d'une voiture dans la DAO
     * @param numSerie
     * @throws DaoException
     */
    void delete(final String numSerie) throws DaoException;

    /**
     * Mise à jour d'une voiture dans la DAO
     * @param voiture
     * @return La voiture mise à jour
     * @throws DaoException
     */
    Voiture update(final Voiture voiture) throws DaoException;

    /**
     * Obtenir la liste de toutes les voitures
     * @return La liste de toutes les voitures
     */
    List<Voiture> readAll();

    boolean exist(Voiture voiture) throws DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }

}
