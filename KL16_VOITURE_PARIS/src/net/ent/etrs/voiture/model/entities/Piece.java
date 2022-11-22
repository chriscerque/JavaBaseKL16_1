package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.ConstructionPieceException;
import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.PieceException;
import net.ent.etrs.voiture.model.entities.references.TypePiece;

import java.util.Objects;

public class Piece extends AbstractEntity{

    // attributs

    private TypePiece typePiece;

    // constructeur

    public Piece(String numSerie, TypePiece typePiece) throws PieceException{
        super(numSerie);
        try {
            this.setTypePiece(typePiece);
        }catch (PieceException e){
            throw new PieceException(e.getMessage(), e);
        }


    }


    // accesseurs


    public TypePiece getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(TypePiece typePiece) throws PieceException {
        if (Objects.isNull(typePiece)){
            throw new PieceException();
        }
        this.typePiece = typePiece;
    }
}
