package fr.efrei.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Collaborateur {

    protected String nom;
    protected String prenom;
    protected String langagePrefere;
    protected double salaire;
    protected Adresse adresse;
    protected String identifiant;
    protected int nombre = 0;
    public static List<Collaborateur> collaborateurList =  new ArrayList<>();;


    public Collaborateur(String nom, String prenom, String langagePrefere, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.langagePrefere = langagePrefere;
        this.salaire = salaire;
        this.identifiant = String.format("C%03d", ++nombre);
        nombre++;
        collaborateurList.add(this);
    }

    public Collaborateur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        this(nom,prenom,langagePrefere,salaire);
        this.adresse = new Adresse(rue, codePostal, ville, pays);
    }

    public boolean afficher() {
        System.out.println("Prénom : " + prenom + " Nom : " + nom + " langage pref : " + langagePrefere + " salaire :" + salaire);
        return false;
    }

    public static void affichertoutlemonde() {
        for (Collaborateur prog : collaborateurList) {
            prog.afficher();}
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public static List<String> getNomliste (){
        List<String> list = new ArrayList<>();
        for (Collaborateur collaborateur : Collaborateur.collaborateurList) {
            list.add(collaborateur.nom);
        }
        return list;
    }

    public void setAdresse(Collaborateur collaborateur, String rue, int codePostal, String ville, String pays) {
        collaborateur.adresse = new Adresse(rue,codePostal,ville,pays);
    }

    public void augmentation(double augmentation) {
        if ((salaire * (1 + (augmentation / 100))) < 0) {
            System.out.println("Salaire négatif");
        } else {
            this.salaire = this.salaire * (1 + (augmentation / 100));
        }
    }

    public double getSalaire() {
        return salaire;
    }

    public abstract void travailler();

    public static void chercherCollaborateurById(String identifiant) {
        for (int i = 0; i < collaborateurList.size(); i++) {
            if (collaborateurList.get(i).getIdentifiant().equals(identifiant)) {
                System.out.println(collaborateurList.get(i));
            }
        }
    }


    public static void chercherCollaborateurByName(String nom) {
        for (int i = 0; i < collaborateurList.size(); i++) {
            if (collaborateurList.get(i).getNom().equals(nom) & !collaborateurList.get(i).getNom().isBlank() ) {
                System.out.println(collaborateurList.get(i));
            }
        }
    }

    public static void afficherCollaborateurSeuil(int seuil) {
        for (int i = 0; i < collaborateurList.size(); i++) {
            if (collaborateurList.get(i).getSalaire() > seuil ) {
                System.out.println(collaborateurList.get(i));
            }
        }
    }

    public static void listtrie() {
         Collaborateur.getNomliste().sort(null);
    }



    public static Collaborateur creerCollaborateur(Scanner scanner) {
        int choix;
        do {
            System.out.println("1 - fr.efrei.java.Programmeur, 2 - fr.efrei.java.Testeur");
            choix = scanner.nextInt();
            scanner.nextLine();
        } while (choix != 1 && choix != 2);

        String prenom;
        String nom;
        String langage;
        double salaire;
        String rue;
        int codePostal;
        String ville;
        String pays;

        do {
            System.out.println("Entrez votre prenom :");
            prenom = scanner.nextLine();
            System.out.println("Entrez votre nom :");
            nom = scanner.nextLine();
            System.out.println("Entrez votre langage préféré :");
            langage = scanner.nextLine();
            System.out.println("Entrez votre salaire :");
            salaire = scanner.nextDouble();
            System.out.println("Entrez votre rue :");
            rue = scanner.nextLine();
            System.out.println("Entrez votre codePostal :");
            codePostal = scanner.nextInt();
            System.out.println("Entrez votre ville :");
            ville = scanner.nextLine();
            System.out.println("Entrez votre pays :");
            pays = scanner.nextLine();
            scanner.nextLine();
        } while (prenom.isBlank() || nom.isBlank() || langage.isBlank() || rue.isBlank() || codePostal < 1000 || codePostal > 99999|| ville.isBlank() || pays.isBlank() );

        if (choix == 1) {
            return Programmeur.creerProgrammeur(nom, prenom, langage, salaire, rue, codePostal,  ville,  pays);
        }
        return Testeur.creerTesteur(nom, prenom, langage, salaire, rue, codePostal,  ville,  pays);
    }
}
