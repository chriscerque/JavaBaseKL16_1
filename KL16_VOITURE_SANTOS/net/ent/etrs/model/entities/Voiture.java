package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.exceptions.VoitureException;
import net.ent.etrs.voiture.model.entities.references.Constantes;
import net.ent.etrs.voiture.model.entities.references.Couleur;

import java.util.List;

public class Voiture extends AbstractEntity {


    private String marque ;

    private String model ;

    private Couleur couleur ;
    private List piece ;



    public Voiture(String numSerie, String marque, String model) throws VoitureException, NumSerieException {
        super(numSerie);
        setMarque (marque);
        setModel( model);
    }

    public String getMarque() {
        return this.marque;
    }

    private void setMarque(String marque) throws VoitureException {
        if (marque == null || marque.isBlank()|| marque.length()<3 ||marque.length()>20) {
            throw new VoitureException(Constantes.VOITURE_MARQUE_NULL_EXCEPTION);
        }

        this.marque = marque;
    }

        public String getModel () {
            return this.model;
        }

        private void setModel (String model ) throws VoitureException {
            if (model == null || model.isBlank()||model.length()<3 ||model.length()>20) {
                throw new VoitureException(Constantes.VOITURE_MODEL_NULL_EXCEPTION);
            }

            this.model = model;
        }

            public Couleur getCouleur () {
                return this.couleur;
            }

            public void setCouleur (Couleur couleur) throws VoitureException {
                if (couleur == null) {
                    throw new VoitureException(Constantes.VOITURE_COULEUR_NULL_EXCEPTION);
                }

                this.couleur = couleur;
            }



    public List getPiece(List piece) {
        return this.piece;
    }

    public Object getLibelle() {

        return null;
    }
}
