package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.CouleurException;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

/**
 * The type Piece.
 */
public class Piece extends AbstractEntity{
    //ATTRIBUTS

    private TypePiece typepiece;

    //CONSTRUCTEURS

    /**
     * Instantiates a new Abstract entity.
     *
     * @param numSerie  the num serie
     * @param typePiece the type piece
     * @throws NumSerieException the num serie exception
     * @throws PieceException    the piece exception
     */
    protected Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, PieceException {
        super(numSerie);
        try {
            this.setTypepiece(typepiece);
        } catch (TypePieceException e) {
            throw new PieceException(e.getMessage(),e);
        }

    }

 //ACCESSEURS

    /**
     * Gets typepiece.
     *
     * @return the typepiece
     */
    public TypePiece getTypepiece() {
        return typepiece;
    }

    /**
     * Sets typepiece.
     *
     * @param typepiece the typepiece
     * @throws TypePieceException the type piece exception
     */
    private void setTypepiece(TypePiece typepiece) throws TypePieceException {
        if (typepiece == null ) {
            throw new TypePieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typepiece = typepiece;
    }


    //EQUALS & HASHCODE

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    //TOSTRING


     //AUTRES METHODES
}
