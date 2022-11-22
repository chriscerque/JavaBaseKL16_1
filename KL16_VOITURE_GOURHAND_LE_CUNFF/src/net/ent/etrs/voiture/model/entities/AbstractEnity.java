package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEnity {
    private String numSerie;

    protected AbstractEnity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(final String numSerie) throws NumSerieException {
        if (numSerie == null || numSerie.isBlank()) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEnity)) return false;
        AbstractEnity that = (AbstractEnity) o;
        return Objects.equals(getNumSerie(), that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }
}
