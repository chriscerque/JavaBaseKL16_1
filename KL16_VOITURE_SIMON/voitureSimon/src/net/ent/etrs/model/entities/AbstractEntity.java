package net.ent.etrs.model.entities;


import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

/**
 * The type Abstract entity.
 */
public abstract class  AbstractEntity {

    //Attributs
    private String numSerie;

    //Constructeur

    /**
     * Instantiates a new Abstract entity.
     *
     * @param numSerie the num serie
     * @throws NumSerieException the num serie exception
     */
    protected AbstractEntity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    //Accesseurs

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
    public void setNumSerie(final String numSerie) throws NumSerieException {
        if (Objects.isNull(numSerie)){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != 5){
            throw new NumSerieException(String.valueOf(ConstantesMetier.NUM_SERIE_TAILLE));
        }
        this.numSerie = numSerie;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }

    //Autres méthodes
}
