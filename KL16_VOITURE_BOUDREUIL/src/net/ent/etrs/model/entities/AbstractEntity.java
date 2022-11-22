package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEntity {

    // Attribut(s)

    private String numSerie;

    // Constructeur(s)

    public AbstractEntity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    // Accesseur(s)

    public String getNumSerie() {
        return this.numSerie;
    }

    public void setNumSerie(final String numSerie) throws NumSerieException {
        if (numSerie == null || numSerie.isBlank()) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

    // Equals & Hashcode

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
}
