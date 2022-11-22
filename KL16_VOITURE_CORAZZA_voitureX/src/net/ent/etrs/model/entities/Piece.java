package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.references.TypePiece;

public class Piece extends AbstractEntity{

    private TypePiece typePiece;

    protected Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, TypePieceException {
        super(numSerie);
        setTypePiece(typePiece);
    }

    // getter
    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    // setter
    public void setTypePiece(final TypePiece typePiece) throws TypePieceException {
        if(typePiece == null){
            throw new TypePieceException("type pièce non renseigné");
        }
        this.typePiece = typePiece;
    }
}
