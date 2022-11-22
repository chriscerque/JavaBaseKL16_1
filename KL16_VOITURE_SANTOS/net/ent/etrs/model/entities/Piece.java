package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.Constantes;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.model.entities.exceptions.PieceException;

import java.util.List;

public class Piece extends AbstractEntity {

    private TypePiece piece;

    public Piece(String numSerie, TypePiece piece) throws net.ent.etrs.model.entities.exceptions.NumSerieException, PieceException {
        super(numSerie);
        setPiece (piece);
    }

    public TypePiece getPiece() {
        return this.piece;
    }

    public void setPiece(TypePiece piece) throws PieceException {
        if (piece == null) {
            throw new PieceException(Constantes.PIECE_PIECE_NULL_EXCEPTION);
        }

        this.piece = piece;
    }


}
