package net.ent.etrs.kl16voituregouin.model.entities.references;


import net.ent.etrs.kl16voituregouin.model.entities.exceptions.TypePieceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TypePiece {

    ROUE_AVD( FamilleTypePiece.ROUE,  Position.AVD, "Roue avant droite"),
    ROUE_AVG( FamilleTypePiece.ROUE , Position.AVG, "Roue avant gauche"),
    ROUE_ARD( FamilleTypePiece.ROUE, Position.ARD, "Roue arrière droite"),
    ROUE_ARG( FamilleTypePiece.ROUE, Position.ARG, "Roue arrière gauche"),
    MOTEUR_V6_AR( FamilleTypePiece.MOTEUR, Position.AR, "Moteur V6 arrière"),
    MOTEUR_V6_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur V6 avant"),
    MOTEUR_V12_AR( FamilleTypePiece.MOTEUR, Position.AR, "Moteur V12 arrière"),
    MOTEUR_V12_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur V12 avant"),
    MOTEUR_1_6_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur 1 litre 6 HDI avant"),
    MOTEUR_1_9_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV,  "Moteur 1 litre 9 HDI avant"),
    MOTEUR_2_0_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV,  "Moteur 2 litre HDI avant"),
    VOLANT_CLASSIQUE_D( FamilleTypePiece.VOLANT, Position.D,  "Volant classique droite"),
    VOLANT_CLASSIQUE_G( FamilleTypePiece.VOLANT, Position.G,  "Volant classique gauche"),
    VOLANT_SPORT_D( FamilleTypePiece.VOLANT, Position.D,  "Volant sport droite"),
    VOLANT_SPORT_G( FamilleTypePiece.VOLANT, Position.G,  "Volant sport gauche"),
    SIEGE_AVD( FamilleTypePiece.SIEGE, Position.AVD,  "Siège avant droite"),
    SIEGE_AVG( FamilleTypePiece.SIEGE, Position.AVG,  "Siège avant gauche"),
    SIEGE_ARD( FamilleTypePiece.SIEGE, Position.ARD,  "Siège arrière droite"),
    SIEGE_ARG( FamilleTypePiece.SIEGE, Position.ARG,  "Siège arrière gauche"),
    PORTIERE_AVD( FamilleTypePiece.PORTIERE, Position.AVD,  "Portière avant droite"),
    PORTIERE_AVG( FamilleTypePiece.PORTIERE, Position.AVG,  "Portière avant gauche"),
    PORTIERE_ARD( FamilleTypePiece.PORTIERE, Position.ARD,  "Portière arrière droite"),
    PORTIERE_ARG( FamilleTypePiece.PORTIERE, Position.ARG,  "Portière arrière gauche");


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
        // à implémenter
        if(lib == null || lib.isBlank()){
            throw new TypePieceException(ConstantesMetier.MSG_PIECE_TYPE_PIECE_NON_RENSEIGNE_EXCEPTION);
        }
        for (TypePiece typePiece : TypePiece.values()) {
            if(typePiece.getLibelle().equals(lib)){
                return typePiece;
            }
        }
        throw new TypePieceException(ConstantesMetier.MSG_TYPE_PIECE_NON_TROUVE_EXCEPTION);
    }

    /**
     * Retourne le libellé du TypePiece.
     * @return la {@link FamilleTypePiece} et la {@link Position}
     */
    public String getLibelle() {
        // à implémenter j'aurais pu utilisé le toString() mais je pense que l'on a pas le droit
        return this.libelle;
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
        // à implémenter
        List<TypePiece> lstPiece = new ArrayList<>();
        for (TypePiece typePiece: TypePiece.values()) {
            if(familleTypePiece.equals(typePiece.getFamilleTypePiece())){
                lstPiece.add(typePiece);
            }
        }
        return Collections.unmodifiableList(lstPiece);
    }
}
