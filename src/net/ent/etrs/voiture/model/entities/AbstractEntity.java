package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

/**
 * Représente une entité abstraite de l'application.
 * Elle porte le {@link #numSerie numéro de série} correspondant à l'identité des entités.
 * @author christophe.cerqueira
 */
public abstract class AbstractEntity {

    private String numSerie;

    protected AbstractEntity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    public String getNumSerie() {
        return numSerie;
    }

    /**
     * Affecte le {@link #numSerie numéro de série}.
     * @param numSerie le {@link #numSerie numéro de série} à affecter.
     * @throws NumSerieException si le numéro de série est null ou vide ou s'il ne contient pas {@link ConstantesMetier#NUM_SERIE_TAILLE}
     */
    private void setNumSerie(final String numSerie) throws NumSerieException {
        if(Objects.isNull(numSerie) || numSerie.isBlank()){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if(numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getNumSerie().equals(that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }
}
