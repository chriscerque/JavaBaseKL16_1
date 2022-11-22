package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.exceptions.PieceException;
import net.ent.etrs.model.entities.exceptions.TypePieceException;
import net.ent.etrs.model.entities.exceptions.VoitureException;
import net.ent.etrs.model.entities.references.Couleur;
import net.ent.etrs.model.entities.references.TypePiece;

import java.util.List;

public class EntitiesFactory {

    private EntitiesFactory() {
    }

    private static void ajouterPieces(final Voiture voiture, final List<Piece> pieces){

    }

    private static void controlerRoues(final Voiture voiture){}

    /** retourne une nouvelle instance de Piece.
     *
     * @param numSerie
     * @param typePiece
     * @return
     */
    public static Piece fabriquerPiece(final String numSerie, final TypePiece typePiece) {
        try {
            Piece p = new Piece(numSerie, typePiece);
            return p;
        } catch (NumSerieException | TypePieceException e) {
            throw new RuntimeException(e);
        }
    }

    /** crée une voiture avec une couleur.
     *
     * @param numSerie
     * @param marque
     * @param model
     * @param couleur
     * @return
     * @throws VoitureException
     * @throws NumSerieException
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model, final Couleur couleur) throws VoitureException, NumSerieException {
        try {
            Voiture v = new Voiture(numSerie, marque, model);
            v.setCouleur(couleur);
            return v;
        } catch (NumSerieException | VoitureException e) {
            throw new RuntimeException(e);
        }
    }

    /** crée une voiture sans couleur.
     *
     * @param numSerie
     * @param marque
     * @param model
     * @return
     * @throws VoitureException
     * @throws NumSerieException
     */
    public static Voiture fabriquerVoiture(final String numSerie, final String marque, final String model) throws VoitureException, NumSerieException {
        try {
            Voiture v = new Voiture(numSerie, marque, model);
            return v;
        } catch (NumSerieException | VoitureException e) {
            throw new RuntimeException(e);
        }
    }
}
