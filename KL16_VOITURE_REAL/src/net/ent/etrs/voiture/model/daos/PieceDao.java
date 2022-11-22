package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.daos.exceptions.DaoException;
import net.ent.etrs.voiture.model.entities.Piece;
import net.ent.etrs.voiture.model.entities.Voiture;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.List;

public interface PieceDao {

    void create(final Piece piece);

    Piece read(final String numSerie);

    void delete(final String numSerie);

    Piece update(final Piece piece) throws DaoException;

    List<Piece> readAll();

}
