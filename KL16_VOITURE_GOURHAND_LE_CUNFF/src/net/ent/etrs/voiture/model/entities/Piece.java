package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

public class Piece extends AbstractEnity {
    private TypePiece typePiece;

    public Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, PieceException {
        super(numSerie);
        this.setTypePiece(typePiece);
    }

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    private void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (typePiece == null) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }
}
