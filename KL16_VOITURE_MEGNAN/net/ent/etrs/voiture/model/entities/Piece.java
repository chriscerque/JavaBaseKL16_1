package net.ent.etrs.voiture.voiture.model.entities;

import net.ent.etrs.voiture.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.voiture.model.entities.references.TypePiece;

import java.util.Objects;

public class Piece extends AbstractEntity {

    //Attribut
    private TypePiece typePiece;

    //Constructeur
    public Piece(String numSerie, TypePiece typePiece) {
        super(numSerie);
        this.typePiece = typePiece;
    }

    //Ascesseurs
    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (Objects.isNull(this.typePiece)){
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }
}
