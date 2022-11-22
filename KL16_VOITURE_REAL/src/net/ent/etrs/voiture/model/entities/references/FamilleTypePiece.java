package net.ent.etrs.voiture.model.entities.references;

import net.ent.etrs.voiture.model.entities.exceptions.FamilleTypePieceException;

import java.util.List;

public enum FamilleTypePiece {

    MOTEUR("moteur"),
    SIEGE("siège"),
    ROUE("roue"),
    VOLANT("volant"),
    PORTIERE("portière");

    private final String libelle;

    FamilleTypePiece(String libelle) {
        this.libelle = libelle;
    }

	/**
     * Retourne la FamilleTypePiece en fonction de son libelle.
     * @param lib le libellé de la FamilleTypePiece
     * @return la FamilleTypePiece trouvée.
     * @throws FamilleTypePieceException si aucune FamilleTypePiece n'est trouvée.
     */
    public static FamilleTypePiece getByLibelle(String lib) throws FamilleTypePieceException {
        for (FamilleTypePiece typePiece : FamilleTypePiece.values()){
            if(typePiece.getLibelle().equals(lib)){
                return typePiece;
            }
        }
        return null;
    }

    public String getLibelle() {
        return libelle;
    }
}
