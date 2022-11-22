package net.ent.etrs.model.entities.references;

import net.ent.etrs.model.entities.exceptions.FamilleTypePieceException;

import java.util.Objects;

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
     *
     * @param lib le libellé de la FamilleTypePiece
     * @return la FamilleTypePiece trouvée.
     * @throws FamilleTypePieceException si aucune FamilleTypePiece n'est trouvée.
     */
    public static FamilleTypePiece getByLibelle(String lib) throws FamilleTypePieceException {
        for (FamilleTypePiece f : FamilleTypePiece.values()) {
            if (Objects.equals(f.getLibelle(), lib)) {
                throw new FamilleTypePieceException(ConstantesMetier.MSG_FAMILLE_TYPE_PIECE_NON_TROUVE_EXCEPTION);
            }
        }
        return valueOf(lib);
    }

    public String getLibelle() {
        return libelle;
    }
}
