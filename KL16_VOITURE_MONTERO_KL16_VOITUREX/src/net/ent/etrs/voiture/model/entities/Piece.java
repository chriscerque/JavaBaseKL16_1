package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

public class Piece extends AbstractEntity {

    //attribut(s)
    private TypePiece typePiece;

    //constructeur(s)
    protected Piece(final String numSerie, TypePiece typePiece) throws VoitureException, NumSerieException, PieceException {
        super(numSerie);
        this.setTypePiece(typePiece);
    }

    //getter et setter

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    private void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (typePiece == null) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }


    //egal et hash

    //to string

    //autre(s) methode(s)


}



