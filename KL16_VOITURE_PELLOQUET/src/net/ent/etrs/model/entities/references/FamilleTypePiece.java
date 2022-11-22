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
    public static FamilleTypePiece getByLibelle(String lib) throws FamilleTypePieceException {
        for (FamilleTypePiece f: FamilleTypePiece.values()) {
            if (f.getLibelle().equals(lib)) {
                return f;
            }
        }
        throw new FamilleTypePieceException();
    }

    public String getLibelle() {
        return libelle;
    }
}
