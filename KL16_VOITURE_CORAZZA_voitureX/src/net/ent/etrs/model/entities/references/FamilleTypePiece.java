package net.ent.etrs.model.entities.references;

import net.ent.etrs.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;

public enum FamilleTypePiece {

    ROUE("roue"),
    MOTEUR("moteur"),
    VOLANT("volant"),
    SIEGE("siège"),
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
    public static FamilleTypePiece getByLibelle(final String lib) throws FamilleTypePieceException {
        if (lib == null){
            throw new FamilleTypePieceException();
        }
        for (FamilleTypePiece familleTypePiece : FamilleTypePiece.values()){
            if(familleTypePiece.getLibelle().equals(lib)){
                return familleTypePiece;
            }
        }
        return null;
    }

    public String getLibelle() {
        return libelle;
    }
}
