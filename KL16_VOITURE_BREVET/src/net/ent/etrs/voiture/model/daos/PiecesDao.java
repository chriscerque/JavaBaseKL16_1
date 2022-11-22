package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.entities.Piece;

import java.util.List;

/**
 * Interface de la Dao des pieces.
 */
public interface PiecesDao {
    void create(final Piece piece);

    Piece read(final String numSerie);

    Piece update(final Piece piece);

    void delete(final String numSerie);

    boolean exist(final Piece piece);

    List<Piece> readall();
}
