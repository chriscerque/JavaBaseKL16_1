package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.TypePiece;

public class Piece extends AbstractEntity{

    private TypePiece typePiece;

    public Piece(String numSerie, TypePiece typePiece) {
        super(numSerie);
        this.typePiece = typePiece;
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    private void setTypePiece(TypePiece typePiece) {
        this.typePiece = typePiece;
    }


}
