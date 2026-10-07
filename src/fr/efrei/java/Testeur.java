package fr.efrei.java;

import java.util.Scanner;

public class Testeur extends Collaborateur {

    public Testeur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        super(nom, prenom, langagePrefere, salaire, rue, codePostal, ville, pays);
    }

    public Testeur (Scanner scanner){
        super(scanner);
    }

    @Override
    public void travailler() {
        System.out.println(prenom + " exécute une campagne de tests.");
    }
}
