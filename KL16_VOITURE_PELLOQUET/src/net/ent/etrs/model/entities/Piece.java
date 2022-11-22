package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.references.TypePiece;

public class Piece extends AbstractEntity {

    private TypePiece typePiece;

    public Piece(String numSerie, TypePiece typePiece) throws NumSerieException {
        super(numSerie);
        this.setTypePiece(typePiece);
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(TypePiece typePiece) {
        this.typePiece = typePiece;
    }
}
