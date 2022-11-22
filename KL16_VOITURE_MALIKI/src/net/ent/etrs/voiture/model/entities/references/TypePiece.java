package net.ent.etrs.voiture.model.entities.references;

import net.ent.etrs.voiture.model.entities.exceptions.FamilleTypePieceException;
import net.ent.etrs.voiture.model.entities.exceptions.TypePieceException;

import java.util.ArrayList;
import java.util.List;

public enum TypePiece {

    ROUE_AVD( FamilleTypePiece.ROUE,  Position.AVD),
    ROUE_AVG( FamilleTypePiece.ROUE , Position.AVG),
    ROUE_ARD( FamilleTypePiece.ROUE, Position.ARD),
    ROUE_ARG( FamilleTypePiece.ROUE, Position.ARG),
    MOTEUR_V6_AR( FamilleTypePiece.MOTEUR, Position.AR),
    MOTEUR_V6_AV( FamilleTypePiece.MOTEUR, Position.AV),
    MOTEUR_V12_AR( FamilleTypePiece.MOTEUR, Position.AR),
    MOTEUR_V12_AV( FamilleTypePiece.MOTEUR, Position.AV),
    MOTEUR_1_6_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV),
    MOTEUR_1_9_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV),
    MOTEUR_2_0_HDI_AV( FamilleTypePiece.MOTEUR, Position.AV),
    VOLANT_CLASSIQUE_D( FamilleTypePiece.VOLANT, Position.D),
    VOLANT_CLASSIQUE_G( FamilleTypePiece.VOLANT, Position.G),
    VOLANT_SPORT_D( FamilleTypePiece.VOLANT, Position.D),
    VOLANT_SPORT_G( FamilleTypePiece.VOLANT, Position.G),
    SIEGE_AVD( FamilleTypePiece.SIEGE, Position.AVD),
    SIEGE_AVG( FamilleTypePiece.SIEGE, Position.AVG),
    SIEGE_ARD( FamilleTypePiece.SIEGE, Position.ARD),
    SIEGE_ARG( FamilleTypePiece.SIEGE, Position.ARG),
    PORTIERE_AVD( FamilleTypePiece.PORTIERE, Position.AVD),
    PORTIERE_AVG( FamilleTypePiece.PORTIERE, Position.AVG),
    PORTIERE_ARD( FamilleTypePiece.PORTIERE, Position.ARD),
    PORTIERE_ARG( FamilleTypePiece.PORTIERE, Position.ARG);


    private final FamilleTypePiece familleTypePiece;

    private final Position position;

    TypePiece(final FamilleTypePiece familleTypePiece, final Position position) {
        this.position = position;
        this.familleTypePiece = familleTypePiece;
    }

    /**
     * Retourne le TypePiece en fonction de son libelle.
     * @param lib le libellé du TypePiece
     * @return le TypePiece trouvé.
     * @throws TypePieceException si aucune TypePiece n'est trouvé.
     */
    public static TypePiece getByLibelle(String lib) throws TypePieceException {
            for (TypePiece g : TypePiece.values()) {
                if (g.getLibelle().equals(lib)) {
                    return g;
                }
            }
            throw new TypePieceException(String.format(ConstantesMetier.MSG_FAMILLE_TYPE_PIECE_NON_TROUVE_EXCEPTION, lib));
    }

    /**
     * Retourne le libellé du TypePiece.
     * @return la {@link FamilleTypePiece} et la {@link Position}
     */
    public String getLibelle() {
       return this.getLibelle();
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
    public static List<TypePiece> listerPieceByFamille(final FamilleTypePiece familleTypePiece) throws FamilleTypePieceException {
        List<TypePiece> lst = new ArrayList<>();
        for (TypePiece type : TypePiece.values()) {
            if (type.getFamilleTypePiece().equals(familleTypePiece)) {
                lst.add(type);
            }
        }
        if (lst.size() != 0) {
            return lst;
        }
        throw new FamilleTypePieceException(String.format(ConstantesMetier.MSG_FAMILLE_TYPE_PIECE_NON_TROUVE_EXCEPTION, familleTypePiece));
    }
}
