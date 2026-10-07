package fr.efrei.java;

public class Programmeur extends Collaborateur implements Formateur {

    public Programmeur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        super(nom, prenom, langagePrefere, salaire, rue, codePostal, ville, pays);
    }

    @Override
    public void travailler() {
        System.out.println(prenom + " développe une fonctionnalité.");
    }

    @Override
    public void former(){
        System.out.println("je suis formateur");
    }


    public static Programmeur creerProgrammeur(String nom, String prenom, String langagePrefere,
                                               double salaire, String rue, int codePostal, String ville, String pays) {
        return new Programmeur(nom, prenom, langagePrefere, salaire ,rue,codePostal,ville, pays);
    }

}
