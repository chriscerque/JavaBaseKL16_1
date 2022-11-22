package net.ent.etrs.KL16_VOITURE.Model.entities;

import net.ent.etrs.KL16_VOITURE.Model.entities.excpetions.AbstractEntityExcpetion;
import net.ent.etrs.KL16_VOITURE.Model.entities.references.ConstantesMetier;

import java.util.Objects;

/**
 * Creation de la classe AbstractEntity.
 */

public abstract class AbstractEntity {
    //ATTRIBUT
    private String numSerie;

    //CONSTRUCTEUR

    protected AbstractEntity(final String numSerie) {
        try {
            this.setNumSerie(numSerie);
        } catch (AbstractEntityExcpetion e) {
            throw new RuntimeException(e);
        }
    }


    //SETTER

    private void setNumSerie(final String numSerie) throws AbstractEntityExcpetion {
        if((Objects.isNull(numSerie)){
            throw new AbstractEntityExcpetion(ConstantesMetier.MSG_NUM_SERIE_NON_RENSEIGNE_EXCEPTION);
        }
        if(numSerie.length() != ConstantesMetier.NUM_SERIE_TAILLE ){
            throw new AbstractEntityExcpetion(ConstantesMetier.MSG_NUM_SERIE_TAILLE_EXCEPTION);
        }

        this.numSerie = numSerie;
    }

    //EQUALS HASHCODE

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(numSerie, that.numSerie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numSerie);
    }

    //GETTER

    public String getNumSerie() {
        return this.numSerie;
    }
}
