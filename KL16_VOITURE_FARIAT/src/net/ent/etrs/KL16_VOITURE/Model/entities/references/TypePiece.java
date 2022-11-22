package net.ent.etrs.KL16_VOITURE.Model.entities.references;

import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.TypePieceException;
import net.ent.etrs.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;

import java.util.List;

/**
 * Creation enum TypePiece.
 */
public enum TypePiece {

    ROUE_AVD( FamilleTypePiece.ROUE,  Position.AVD, "Roue avant droite"),
    ROUE_AVG( FamilleTypePiece.ROUE , Position.AVG, "Roue avant gauche"),
    ROUE_ARD( FamilleTypePiece.ROUE, Position.ARD, "Roue arriere droite"),
    ROUE_ARG( FamilleTypePiece.ROUE, Position.ARG, "Roue arriere gauche"),
    MOTEUR_V6_AR( FamilleTypePiece.MOTEUR, Position.AR, "Moteur V6 arriere"),
    MOTEUR_V6_AV( FamilleTypePiece.MOTEUR, Position.AV, "Moteur V6 avant"),
    MOTEUR_V12_AR( FamilleTypePiece.MOTEUR, Position.AR, "moteur V12 arriere"),
    MOTEUR_V12_AV( FamilleTypePiece.MOTEUR, Position.AV, "moteur V12 avant"),
    MOTEUR_1_6_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "moteur 1.6 HDI avant"),
    MOTEUR_1_9_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV,  "moteur 1.6 HDI avant"),
    MOTEUR_2_0_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV, "moteur 2.0 HDI avant"),
    VOLANT_CLASSIQUE_D( FamilleTypePiece.VOLANT, Position.D, "volant classique droite"),
    VOLANT_CLASSIQUE_G( FamilleTypePiece.VOLANT, Position.G, "volant classique gauche"),
    VOLANT_SPORT_D( FamilleTypePiece.VOLANT, Position.D, "volant sport droit"),
    VOLANT_SPORT_G( FamilleTypePiece.VOLANT, Position.G, "volant sport gauche"),
    SIEGE_AVD( FamilleTypePiece.SIEGE, Position.AVD, "siege avant droite"),
    SIEGE_AVG( FamilleTypePiece.SIEGE, Position.AVG, "siege avant gauche"),
    SIEGE_ARD( FamilleTypePiece.SIEGE, Position.ARD, "siege arriere droite"),
    SIEGE_ARG( FamilleTypePiece.SIEGE, Position.ARG, "siege arriere gauche"),
    PORTIERE_AVD( FamilleTypePiece.PORTIERE, Position.AVD, "portiere avant droite"),
    PORTIERE_AVG( FamilleTypePiece.PORTIERE, Position.AVG, "portiere avant gauche"),
    PORTIERE_ARD( FamilleTypePiece.PORTIERE, Position.ARD, "portiere arriere droite"),
    PORTIERE_ARG( FamilleTypePiece.PORTIERE, Position.ARG, "portiere arriere gauche");

//ATTRIBUTS
    private final FamilleTypePiece familleTypePiece;

    private final Position position;

    private final String libelle;

//CONSTRUCTEUR

    TypePiece(final FamilleTypePiece familleTypePiece, final Position position, final String libelle) {
        this.familleTypePiece = familleTypePiece;
        this.position = position;
        this.libelle = libelle;
    }



    /**
     * Retourne le TypePiece en fonction de son libelle.
     * @param lib le libellé du TypePiece
     * @return le TypePiece trouvé.
     * @throws TypePieceException si aucune TypePiece n'est trouvé.
     */
    public static TypePiece getByLibelle(String lib) throws TypePieceException {
        if(lib==null){
            throw new TypePieceException(ConstantesMetier.MSG_TYPE_PIECE_NON_TROUVE_EXCEPTION);
        }
        for (TypePiece  typePiece  : TypePiece .values()){
            if(typePiece.getLibelle().equals(lib)){
                return typePiece;
            }
        }
        return null;
    }

    /**
     * Retourne le libellé du TypePiece.
     *
     * @return la {@link FamilleTypePiece} et la {@link Position}
     */
    public FamilleTypePiece getLibelle() {

        return this.getFamilleTypePiece();
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
        //TODO à implémenter
    }
}
