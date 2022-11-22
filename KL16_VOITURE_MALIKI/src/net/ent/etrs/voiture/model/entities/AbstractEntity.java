package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

/**
 * The type Abstract entity.
 */
public abstract class AbstractEntity {

    //ATTRIBUTS
    private String numSerie;


    //CONSTRUCTEURS

    /**
     * Instantiates a new Abstract entity.
     *
     * @param numSerie the num serie
     * @throws NumSerieException the num serie exception
     */
    protected AbstractEntity(final String numSerie) throws NumSerieException {
        try {
            this.setNumSerie(numSerie);
        } catch (NumSerieException e) {
            throw new NumSerieException(e.getMessage(),e);
        }
    }


    //ACCESSEURS

    /**
     * Gets num serie.
     *
     * @return the num serie
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * Sets num serie.
     *
     * @param numSerie the num serie
     * @throws NumSerieException the num serie exception
     */
    private void setNumSerie(final String numSerie) throws NumSerieException {
        if (numSerie == null || numSerie.length() < ConstantesMetier.NUM_SERIE_TAILLE) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
    }
        this.numSerie = numSerie;
    }


    //EQUALS & HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }


    //TOSTRING

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "numSerie='" + numSerie + '\'' +
                '}';
    }


    //AUTRES METHODES
}
