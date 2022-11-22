package net.ent.etrs.model.entities;

import net.ent.etrs.model.entities.exceptions.NumSerieException;
import net.ent.etrs.model.entities.references.ConstantesMetier;

import java.util.Objects;

public abstract class AbstractEntity {


    //Attributs.
    private String numSerie;

    //Constructeur.

    public AbstractEntity(String numSerie) throws NumSerieException {
        try {
            this.setNumSerie(numSerie);
        } catch (NumSerieException e) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
    }

    //Getters setters.

    public String getNumSerie() {
        return numSerie;
    }

    //Vérifier que numSerie n'est pas nul, vide ou ne fait pas "NUM_SERIE_TAILLE" (soit 5 pour le moment).
    public void setNumSerie(String numSerie) throws NumSerieException {
        if (Objects.isNull(numSerie) || numSerie.isBlank())
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        if (numSerie.length() == ConstantesMetier.NUM_SERIE_TAILLE) {
            throw new NumSerieException(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }
        this.numSerie = numSerie;
    }

    //Equals hashcode sur numSerie.

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
}
