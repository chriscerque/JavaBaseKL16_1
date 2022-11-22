package net.ent.etrs.commons;

public class GestionException {
    private GestionException() {
    }

    public static String arborescenceExceptionToString(Throwable e) {
        StringBuilder sb = new StringBuilder("ERREUR : " + System.lineSeparator());
        String tabCounter = "";
        while (e != null) {
            sb.append(tabCounter);
            sb.append(e.getMessage());
            sb.append(System.lineSeparator());
            e = e.getCause();
            tabCounter += "\t";
        }
        return sb.toString();
    }

    public static String lastExceptionToString(Throwable e) {
        StringBuilder sb = new StringBuilder("ERREUR : " + System.lineSeparator());
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return sb.append(e.getMessage()).toString();
    }

    public static void afficherMessageErreur(Throwable e) {
        System.out.println(arborescenceExceptionToString(e));
    }
}
