package net.ent.etrs.voiture.model.entities.references;

import net.ent.etrs.voiture.model.entities.exceptions.FamilleTypePieceException;

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
        for (FamilleTypePiece familleTypePiece : FamilleTypePiece.values()){
            if(familleTypePiece.getLibelle().equals(lib)){
                return familleTypePiece;
            }
        }
        throw new FamilleTypePieceException(ConstantesMetier.MSG_FAMILLE_TYPE_PIECE_NON_TROUVE_EXCEPTION);
    }

    public String getLibelle() {
        return libelle;
    }
}
