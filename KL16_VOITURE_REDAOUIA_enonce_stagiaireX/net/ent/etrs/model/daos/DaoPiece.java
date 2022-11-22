package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;

import java.util.List;

public interface DaoPiece {

    Piece create(final Piece piece) throws  DaoException;

    Piece read(final String numSerie) throws DaoException;

    Piece update(final Piece piece) throws DaoException;

    void delete(final String numSerie) throws DaoException;

    boolean exist(final Piece piece) throws DaoException;

    List<Piece> readAll();

    void init();
}
