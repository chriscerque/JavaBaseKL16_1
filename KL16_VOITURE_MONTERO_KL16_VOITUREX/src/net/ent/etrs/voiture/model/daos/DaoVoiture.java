package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;

import java.util.List;

public interface DaoVoiture {

    Voiture create(Voiture voiture) throws DaoException;

    Voiture read(String numSerie) throws DaoException;

    void delete(String numSerie) throws DaoException;

    Voiture update(Voiture voiture) throws DaoException;

    List<Voiture> readAll();

    boolean exist(Voiture voiture) throws DaoException;

    List<Piece> readAllPiecesByVoiture(String numSerie) throws DaoException, VoitureException;
}

