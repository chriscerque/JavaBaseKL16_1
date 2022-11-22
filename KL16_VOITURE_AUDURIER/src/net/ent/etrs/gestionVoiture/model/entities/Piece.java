package net.ent.etrs.gestionVoiture.model.entities;

import net.ent.etrs.gestionVoiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.gestionVoiture.model.entities.exceptions.PieceException;
import net.ent.etrs.gestionVoiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.gestionVoiture.model.entities.references.TypePiece;

public class Piece extends AbstractEntity {
    //Attributs
    private TypePiece typePiece;

    //Constructor

    protected Piece(final String numSerie, final TypePiece typePiece)
            throws ConstructionPieceException, NumSerieException {
        super(numSerie);
        try {
            this.setTypePiece(typePiece);
        } catch (PieceException e) {
            throw new ConstructionPieceException(ConstantesMetier.MSG_CONSTRUCTION_PIECE_EXCEPTION);
        }
    }

    //Getter/Setter

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (typePiece == null) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }

    //To String

    @Override
    public String toString() {
        return "Piece{" +
                "typePiece=" + typePiece +
                "} " + super.toString();
    }
}
