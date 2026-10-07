package fr.efrei.java;

public class CollaborateurDejaExistantException extends RuntimeException {

    public CollaborateurDejaExistantException(String identifiant) {
        super("Un collaborateur possède déjà l'identifiant " + identifiant);
    }


}
