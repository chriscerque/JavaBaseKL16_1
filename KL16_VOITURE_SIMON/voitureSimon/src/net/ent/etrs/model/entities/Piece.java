package net.ent.etrs.model.entities;


import net.ent.etrs.voiture.model.entities.AbstractEntity;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.Objects;

/**
 * The type Piece.
 */
public class Piece extends AbstractEntity {

    //Attribut
    private TypePiece typePiece;

    //Constructeur


    /**
     * Instantiates a new Piece.
     *
     * @param numSerie  the num serie
     * @param typePiece the type piece
     * @throws NumSerieException the num serie exception
     * @throws PieceException    the piece exception
     */
    protected Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, PieceException {
        super(numSerie);
        try {
            this.setTypePiece(typePiece);
        }catch (PieceException e){
            throw new PieceException(e.getMessage(), e);
        }

    }
    //Accesseurs

    /**
     * Gets type piece.
     *
     * @return the type piece
     */
    public TypePiece getTypePiece() {
        return typePiece;
    }

    /**
     * Sets type piece.
     *
     * @param typePiece the type piece
     * @throws PieceException the piece exception
     */
    public void setTypePiece(final TypePiece typePiece) throws PieceException {
        // Vérifie que le typePiece ne soit pas null.
        if (Objects.isNull(typePiece)){
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION );
        }
        this.typePiece = typePiece;
    }

    //Autres méthodes
}
