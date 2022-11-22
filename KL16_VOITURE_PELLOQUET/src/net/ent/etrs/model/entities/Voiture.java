package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.ConstructionVoitureException;
import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.ConstantesMetier;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.ArrayList;
import java.util.List;

public class Voiture extends AbstractEntity{
    private String marque;
    private String modele;
    private Couleur couleur;
    private List<Piece> pieces;

    public Voiture(String numSerie, String marque, String modele) throws NumSerieException {
        super(numSerie);
        this.marque = marque;
        this.modele = modele;
        pieces = new ArrayList<>();
    }

    public String getMarque() {
        return marque;
    }

    private void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    private void setModele(String modele) {
        this.modele = modele;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    protected void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * ajoute une la piece "piece" à la liste de piece de la voiture
     * @param piece piece de voiture souhaité
     * @throws ConstructionVoitureException
     */
    protected void ajouterPiece(Piece piece) throws ConstructionVoitureException {
        for (Piece p : this.pieces) {
            if (p.getTypePiece().getLibelle().equals(piece.getTypePiece().getLibelle())) {
                throw new ConstructionVoitureException(ConstantesMetier.MSG_VOITURE_PIECE_EXISTE_EXCEPTION);
            }
        }
        pieces.add(piece);
    }
}
