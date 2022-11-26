package net.ent.etrs.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.Couleur;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

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
