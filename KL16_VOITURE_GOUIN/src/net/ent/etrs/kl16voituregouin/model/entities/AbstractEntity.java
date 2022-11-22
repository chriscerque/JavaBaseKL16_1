package net.ent.etrs.kl16voituregouin.model.entities;

import net.ent.etrs.kl16voituregouin.model.entities.exceptions.NumSerieException;
import net.ent.etrs.kl16voituregouin.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEntity {

    //region  Attributs

    private String numSerie;

    //endregion

    //region  Constructeurs

    protected AbstractEntity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    //endregion

    //region  Getter & Setter

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(final String numSerie) throws NumSerieException {
        if(numSerie == null || numSerie.isBlank()){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if(numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE){
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

    //endregion

    //region  Méthodes

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getNumSerie().equals(that.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }

    //endregion
}
