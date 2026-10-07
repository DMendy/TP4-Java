package fr.efrei.java;

import java.util.Scanner;

public abstract class Collaborateur {

    protected String identifiant;
    protected String nom;
    protected String prenom;
    protected String langagePrefere;
    protected double salaire;
    protected fr.efrei.java.Adresse adresse;

    public Collaborateur(String nom, String prenom, String langagePrefere, double salaire, fr.efrei.java.Adresse adresse) {
        verifierNom(nom);
        verifierSalaire(salaire);

        this.nom = nom;
        this.prenom = prenom;
        this.langagePrefere = langagePrefere;
        this.salaire = salaire;
        this.adresse = adresse;
    }

    public Collaborateur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        this(nom, prenom, langagePrefere, salaire, new fr.efrei.java.Adresse(rue, codePostal, ville, pays));
    }

    public Collaborateur(Scanner scanner){
        scanner.nextLine();

        System.out.println("Entrez votre le nom");
        String nom = scanner.nextLine();

        System.out.println("Entrez votre le prenom");
        String prenom = scanner.nextLine();

        System.out.println("Entrez votre language préféré");
        String languePreferee = scanner.nextLine();

        System.out.println("Entrez votre salaire");
        double salaire = scanner.nextDouble();

        this(nom, prenom, languePreferee, salaire, new Adresse(scanner));
    }

    private void verifierNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
    }

    private void verifierSalaire(double salaire){
        if (salaire < 0) {
            throw new IllegalArgumentException("Le salaire ne peut pas être négatif");
        }
    }

    public void afficher() {
        System.out.println("Prénom : " + prenom + " Nom : " + nom + " langage pref : " + langagePrefere + " salaire :" + salaire);
    }

    public void augmentation(double augmentation) {
        if ((salaire * (1 + (augmentation / 100))) < 0) {
            System.out.println("Salaire négatif");
        } else {
            this.salaire = this.salaire * (1 + (augmentation / 100));
        }
    }

    public abstract void travailler();

    public double getSalaire() {
        return salaire;
    }

    public String getIdentifiant(){
        return identifiant;
    }
    public void setSalaire(double salaire) {
        verifierSalaire(salaire);
        this.salaire = salaire;
    }

    public String getLangagePrefere() {
        return langagePrefere;
    }

    public void setLangagePrefere(String langagePrefere) {
        this.langagePrefere = langagePrefere;
    }

    public String getPrenom() {
        return prenom;
    }



    public void setPrenom(String prenom) {
        verifierNom(prenom);
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        verifierNom(nom);
        this.nom = nom;
    }

    public void setIdentifiant(String id) {
        this.identifiant = id;
    }
}

