package fr.efrei.java;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class MenuAnnuaire {
    public static void main(String[] args) {

        EntityManagerFactory fabrique = Persistence.createEntityManagerFactory("collaborateurs-pu");
        CollaborateurService service = new CollaborateurService(fabrique);
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("1 - Ajouter Alice (C001)");
            System.out.println("2 - Afficher le nombre de collaborateurs");
            System.out.println("3 - Rechercher un collaborateur par identifiant");
            System.out.println("0 - Quitter");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    Programmeur alice = new Programmeur("Martin", "Alice", "Java",
                            48000, "2 rue des carrières", 92220, "Bagneux", "France");
                    alice.setIdentifiant("C001");
                    try {
                        service.ajouter(alice);
                        System.out.println("Alice (C001) a été ajoutée.");
                    } catch (CollaborateurDejaExistantException e) {
                        System.out.println("Impossible d'ajouter Alice : l'identifiant "
                                + e.identifiant() + " est déjà utilisé.");
                    }
                    break;
                case "2":
                    System.out.println("Nombre de collaborateurs : " + service.nombre());
                    break;
                case "3":
                    System.out.println("Identifiant recherché :");
                    String identifiant = scanner.nextLine();
                    Collaborateur trouve = service.trouver(identifiant);
                    if (trouve == null) {
                        System.out.println("Aucun collaborateur avec l'identifiant " + identifiant + ".");
                    } else {
                        trouve.afficher();
                    }
                    break;
                case "0":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix inconnu.");
            }
        }
        fabrique.close();
    }
}
