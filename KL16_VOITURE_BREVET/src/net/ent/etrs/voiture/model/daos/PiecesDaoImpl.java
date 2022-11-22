package net.ent.etrs.voiture.model.daos;

import net.ent.etrs.voiture.model.entities.Piece;

import java.util.List;

/**
 * Implémentation de {@link PiecesDao}.
 */
public class PiecesDaoImpl implements PiecesDao {
    //region ATTRIBUTS
    List<Piece> persistence;

    //endregion
    //region CONSTRUCTEUR(S)
    protected PiecesDaoImpl() {

    }
    //endregion
    //region MÉTHODES

    @Override
    public void create(final Piece piece) {

    }

    @Override
    public Piece read(final String numSerie) {
        return null;
    }

    @Override
    public Piece update(final Piece piece) {
        return null;
    }

    @Override
    public void delete(final String numSerie) {

    }

    @Override
    public boolean exist(final Piece piece) {
        return false;
    }

    @Override
    public List<Piece> readall() {
        return null;
    }

    //endregion
}
