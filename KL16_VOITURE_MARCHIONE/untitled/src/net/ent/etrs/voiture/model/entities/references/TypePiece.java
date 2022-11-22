package net.ent.etrs.voiture.model.entities.references;

import net.ent.etrs.voiture.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TypePiece {

    ROUE_AVD( FamilleTypePiece.ROUE,  Position.AVD, "Roue avant droite"),
    ROUE_AVG( FamilleTypePiece.ROUE , Position.AVG, "Roue avant gauche"),
    ROUE_ARD( FamilleTypePiece.ROUE, Position.ARD, "Roue arriere droite"),
    ROUE_ARG( FamilleTypePiece.ROUE, Position.ARG, "Roue arriere gauche"),
    MOTEUR_V6_AR( FamilleTypePiece.MOTEUR, Position.AR, "Moteur V6 arriere"),
    MOTEUR_V6_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur V6 avant"),
    MOTEUR_V12_AR( FamilleTypePiece.MOTEUR, Position.AR, "Moteur V12 arriere"),
    MOTEUR_V12_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur V12 avant"),
    MOTEUR_1_6_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur 1 6 HDI avant"),
    MOTEUR_1_9_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur 1 9 HDI avant"),
    MOTEUR_2_0_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur 2 0 HDI avant"),
    VOLANT_CLASSIQUE_D( FamilleTypePiece.VOLANT, Position.D, "Volant classique droite"),
    VOLANT_CLASSIQUE_G( FamilleTypePiece.VOLANT, Position.G, "Volant classique gauche"),
    VOLANT_SPORT_D( FamilleTypePiece.VOLANT, Position.D,"Volant sport droite"),
    VOLANT_SPORT_G( FamilleTypePiece.VOLANT, Position.G, "Volant sport gauche"),
    SIEGE_AVD( FamilleTypePiece.SIEGE, Position.AVD, "Siege avant droite"),
    SIEGE_AVG( FamilleTypePiece.SIEGE, Position.AVG, "Siege avant gauche"),
    SIEGE_ARD( FamilleTypePiece.SIEGE, Position.ARD, "Siege arriere droite"),
    SIEGE_ARG( FamilleTypePiece.SIEGE, Position.ARG, "Siege arriere gauche"),
    PORTIERE_AVD( FamilleTypePiece.PORTIERE, Position.AVD, "Portiere avant droite"),
    PORTIERE_AVG( FamilleTypePiece.PORTIERE, Position.AVG, "Portiere avant gauche"),
    PORTIERE_ARD( FamilleTypePiece.PORTIERE, Position.ARD, "Portiere arriere droite"),
    PORTIERE_ARG( FamilleTypePiece.PORTIERE, Position.ARG, "Portiere arriere gauche");


    private final FamilleTypePiece familleTypePiece;

    private final Position position;

    private final String libelle;

    TypePiece(final FamilleTypePiece familleTypePiece, final Position position, final String libelle) {
        this.position = position;
        this.familleTypePiece = familleTypePiece;
        this.libelle = libelle;
    }

    /**
     * Retourne le TypePiece en fonction de son libelle.
     * @param lib le libellé du TypePiece
     * @return le TypePiece trouvé.
     * @throws TypePieceException si aucune TypePiece n'est trouvé.
     */
    public static TypePiece getByLibelle(String lib) throws TypePieceException {
        for (TypePiece p : TypePiece.values()){
            if (p.getLibelle().equals(lib)) {
                return p;
            }
        }
        throw new TypePieceException(String.format(ConstantesMetier.MSG_TYPE_PIECE_NON_TROUVE_EXCEPTION));
    }

    /**
     * Retourne le libellé du TypePiece.
     * @return la {@link FamilleTypePiece} et la {@link Position}
     */
    public String getLibelle() {
        return libelle;
    }


    public Position getPosition() {
        return position;
    }

    public FamilleTypePiece getFamilleTypePiece() {
        return familleTypePiece;
    }


    /**
     * Retourne la liste des TypePiece en fonction de le leur {@link FamilleTypePiece}.
     * @param familleTypePiece {@link FamilleTypePiece} recherchée.
     * @return la liste des TypePiece
     */
    public static List<TypePiece> listerPieceByFamille(final FamilleTypePiece familleTypePiece){
        return listerPieceByFamille(familleTypePiece);
    }
}
