package net.ent.etrs.KL16_VOITURE.Model.entities;

import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.PieceException;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.ConstantesMetier;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.TypePiece;

public class Piece extends AbstractEntity{
    //ATTRIBUT
    private TypePiece typePiece;

    //CONSTUCTEUR

    protected Piece(final String numSerie, final TypePiece typePiece) {
        super(numSerie);
        this.typePiece = typePiece;
    }


    //GETTER SETTER

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(final TypePiece typePiece) throws PieceException {
        if(typePiece ==null){
            throw new PieceException(ConstantesMetier.MSG_TYPE_PIECE_NON_TROUVE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }
}
