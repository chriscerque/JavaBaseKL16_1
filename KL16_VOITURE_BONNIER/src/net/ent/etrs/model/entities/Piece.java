package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.Objects;

public class Piece extends AbstractEntity {

    //Attributs

    private TypePiece typePiece;

    //Constructeur

    protected Piece(String numSerie, TypePiece typePiece) throws NumSerieException {
        super(numSerie);
        this.typePiece = typePiece;
    }


    //Getters setters


    public TypePiece getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(TypePiece typePiece) throws PieceException {
        if (Objects.isNull(typePiece)) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }
}
