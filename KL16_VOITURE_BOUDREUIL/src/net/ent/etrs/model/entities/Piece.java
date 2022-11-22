package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.TypePiece;

import javax.naming.CompositeName;

public class Piece extends AbstractEntity{

    // Attribut(s)

    private TypePiece typePiece;

    // Constructeur(s)

    public Piece(final String numserie, final TypePiece typePiece) throws NumSerieException, TypePieceException, PieceException {
        super(numserie);
        this.setTypePiece(typePiece);
    }

    // Accesseur(s)

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(final TypePiece typePiece) throws TypePieceException, PieceException {
        if (typePiece == null) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }

}
