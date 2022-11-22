package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.Objects;

/**
 * Représente une entité pièce de l'application.
 * @author christophe.cerqueira
 */
public class Piece extends AbstractEntity{

    /**
     * Représente le {@link TypePiece type de pièce} de la pièce.
     */
    private TypePiece typePiece;

    /**
     * Construit une pièce en fonction des paramètre passés.
     * @param numSerie {@link AbstractEntity numéro de série}
     * @param typePiece {@link #typePiece type de pièce}
     * @throws NumSerieException si le numéro de série est null ou vide ou s'il ne contient pas {@link ConstantesMetier#NUM_SERIE_TAILLE}
     * @throws PieceException si le {@link #typePiece  type de pièce} ne respecte pas les contraintes fixées {@link #setTypePiece(TypePiece)  setTypePiece}.
     */
    protected Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, PieceException {
        super(numSerie);
        this.setTypePiece(typePiece);
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    /**
     * Affecte le {@link #typePiece type de pièce}.
     * @param typePiece la {@link #typePiece type de pièce} à affecter.
     * @throws PieceException si le type de pièce est null.
     */
    private void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (Objects.isNull(typePiece)){
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }


}
