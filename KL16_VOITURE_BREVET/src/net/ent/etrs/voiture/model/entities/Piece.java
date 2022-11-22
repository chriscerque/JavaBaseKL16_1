package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.Objects;

/**
 * Classe héritant de {@link Entity}.
 * Représentant une pièce dans l'application.
 */
public class Piece extends Entity {
    //region ATTRIBUTS
    private TypePiece typePiece;
    //endregion
    //region CONSTRUCTEUR(S)

    protected Piece(final String numSerie, final TypePiece typePiece) throws NumSerieException, PieceException {
        super(numSerie);
        this.setTypePiece(typePiece);

    }

    //endregion
    //region GETTER SETTER

    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    private void setTypePiece(final TypePiece typePiece) throws PieceException {
        if (Objects.isNull(typePiece)) {
            throw new PieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        this.typePiece = typePiece;
    }

    //endregion
}
