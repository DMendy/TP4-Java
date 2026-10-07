package fr.efrei.java;

import java.util.Scanner;

public class Programmeur extends Collaborateur implements Formateur {

    public Programmeur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        super(nom, prenom, langagePrefere, salaire, rue, codePostal, ville, pays);
    }

    public Programmeur(Scanner scanner){
        super(scanner);
    }

    @Override
    public void travailler() {
        System.out.println(prenom + " développe une fonctionnalité.");
    }

    @Override
    public void former(){
        System.out.println("je suis formateur");
    }
}
