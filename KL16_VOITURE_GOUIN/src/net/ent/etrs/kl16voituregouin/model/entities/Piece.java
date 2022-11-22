package net.ent.etrs.kl16voituregouin.model.entities;

import net.ent.etrs.kl16voituregouin.model.entities.exceptions.NumSerieException;
import net.ent.etrs.kl16voituregouin.model.entities.exceptions.PieceException;
import net.ent.etrs.kl16voituregouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16voituregouin.model.entities.references.TypePiece;

public class Piece extends AbstractEntity{

    //region  Attributs
        private TypePiece typePiece;

    //endregion

    //region  Constructeurs

    protected Piece(final String numSerie, final TypePiece typePiece) throws PieceException, NumSerieException {
        super(numSerie);
        try {
            this.setTypePiece(typePiece);
        } catch (PieceException  e) {
            throw new PieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION , e);
        }
    }

    //endregion

    //region  Getter & Setter

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    private void setTypePiece(final TypePiece typePiece) throws PieceException {
        if(typePiece == null){
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }

//endregion


}
