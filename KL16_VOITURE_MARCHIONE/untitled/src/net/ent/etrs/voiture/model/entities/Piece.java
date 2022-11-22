package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.references.TypePiece;

public class Piece {
    /**
     * Classe représantant les pieces.
     */
    //-------------------------------
    //          ATTRIBUTS
    //-------------------------------

    private TypePiece typePiece;

    /**
     * Constructeur.
     * @param typePiece
     */
    public Piece(TypePiece typePiece) {
        this.typePiece = typePiece;
    }

    /**
     * GETTER
     * @return
     */
    public TypePiece getTypePiece() {
        return this.typePiece = typePiece;
    }

    /**
     * SETTER
     * @param typePiece
     */
    private void setTypePiece(TypePiece typePiece) {
        this.typePiece = typePiece;
    }
}
