package net.ent.etrs.voiture.model.entities;

import net.ent.etrs.voiture.model.entities.exceptions.NumSerieException;
import net.ent.etrs.voiture.model.entities.references.ConstantesMetier;

import java.util.Objects;

/**
 * Point de départ de toutes entités de l'application.
 */
public abstract class Entity {
    //region ATTRIBUTS
    private String numSerie;
    //endregion
    //region CONSTRUCTEUR(S)

    protected Entity(final String numSerie) throws NumSerieException {
        this.setNumSerie(numSerie);
    }

    //endregion
    //region GETTER SETTER

    public String getNumSerie() {
        return this.numSerie;
    }

    private void setNumSerie(final String numSerie) throws NumSerieException {
        if (Objects.isNull(numSerie) || numSerie.isBlank()) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if (numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }
    //endregion
    //region Equals / HashCode

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(getNumSerie(), entity.getNumSerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSerie());
    }

    //endregion
}
