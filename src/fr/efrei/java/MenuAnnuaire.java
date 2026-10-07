package fr.efrei.java;

import java.util.Scanner;

public class MenuAnnuaire {
    public static void main(String[] args) {
        Annuaire annuaire = new Annuaire();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("1 - Ajouter Alice (C001)");
            System.out.println("2 - Afficher le nombre de collaborateurs");
            System.out.println("0 - Quitter");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    Programmeur alice = new Programmeur("Martin", "Alice", "Java",
                            48000, "2 rue des carrières", 92220, "Bagneux", "France");
                    alice.setIdentifiant("C001");
                    try {
                        annuaire.ajouter(alice);
                        System.out.println("Alice (C001) a été ajoutée.");
                    } catch (CollaborateurDejaExistantException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Nombre de collaborateurs : " + annuaire.taille());
                    break;
                case "0":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix inconnu.");
            }
        }
    }
}
