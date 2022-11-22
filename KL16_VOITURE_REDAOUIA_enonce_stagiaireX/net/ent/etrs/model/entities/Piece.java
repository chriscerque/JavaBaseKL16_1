package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.references.TypePiece;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.exceptions.NumSerieException;

public class Piece extends AbstractEntity {

    private TypePiece typePiece;


    protected Piece(String numSerie, TypePiece typePiece) throws TypePieceException, NumSerieException {

            super(numSerie);
        try {
            this.setTypePiece(typePiece);
        }
        catch (TypePieceException e){
            throw new TypePieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION, e.getCause());
        }

    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(TypePiece typePiece) throws TypePieceException {
        if (typePiece == null){
            throw new TypePieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }
}
