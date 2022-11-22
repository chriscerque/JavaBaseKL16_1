package net.ent.etrs.gestionVoiture.model.entities.references;

public enum Position {
    AVD("avant droite"),
    AVG("avant gauche"),
    ARD("arrière droite"),
    ARG("arrière gauche"),
    D("droite"),
    G("gauche"),
    AV("avant"),
    AR("arrière");

    private final String libelle;

    Position(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
