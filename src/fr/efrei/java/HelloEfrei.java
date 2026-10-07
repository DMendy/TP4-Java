package fr.efrei.java;

import java.util.*;

public class HelloEfrei {
    public static void main(String[] args) {

        Adresse adresse = new Adresse("2 rue des carrières",92220,"Bagneux","France");

        Programmeur Doryan = new Programmeur("Mendy", "Doryan", "Java", 10000, adresse.getRue(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays());
        Programmeur Alice = new Programmeur("Martin", "Alice", "Java", 48000, adresse.getRue(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays());
        Programmeur Alex = new Programmeur("Dupont", "Alex", "Python", 45000, adresse.getRue(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays());
//        fr.efrei.java.Formateur formateur = Alice;
//        formateur.former();
//        System.out.println(Doryan.afficher());
//        Doryan.augmentation(5);
//        System.out.println(Doryan.afficher());


        System.out.println("--- Tous les programmeurs ---");
        Collaborateur.affichertoutlemonde();

        System.out.println("--- Noms contenant \"Mart\" ---");
        Collaborateur.chercherCollaborateurByName("Mart");

        System.out.println("--- Salaires supérieurs à 46000 ---");
        Collaborateur.afficherCollaborateurSeuil(46000);

        System.out.println("--- Collaborateurs triés par nom ---");
        Collaborateur.affichertoutlemonde();

        System.out.println("--- Collaborateurs triés par id ---");
        Collaborateur.chercherCollaborateurById("C001");


        Map<String, Collaborateur> mamap = new HashMap<>();
        for (Collaborateur collaborateur : Collaborateur.collaborateurList) {
            mamap.put(collaborateur.getIdentifiant(),collaborateur);
            mamap.containsKey("C002");
        }

        boolean menu = true;

        Collaborateur[] collaborateurs = { Doryan, Alex, Alice };
        for (int i = 0; i < collaborateurs.length ;i++) {
            collaborateurs[i].travailler();
        }




//        Scanner scanner = new Scanner(System.in);
//        Integer choix;
//        while (menu) {
//            System.out.println("==============================\n" +
//                    " Gestion des collaborateurs\n" +
//                    "==============================\n" +
//                    "\n" +
//                    "1 - Afficher Alice\n" +
//                    "2 - Afficher Alex\n" +
//                    "3 - Ajouter un collaborateur\n" +
//                    "4 - Augmenter un collaborateur\n" +
//                    "0 - Quitter");
//            choix = scanner.nextInt();
//            scanner.nextLine();
//            switch (choix) {
//                case 1:
//                    Alice.afficher();
//                    break;
//                case 2:
//                    Alex.afficher();
//                    break;
//                case 3:
//                    fr.efrei.java.Collaborateur nouveau = fr.efrei.java.Collaborateur.creerCollaborateur(scanner);
//                    nouveau.afficher();
//                    break;
//                case 4:
//                    for (int i = 0; i < collaborateurs.length ;i++) {
//                        collaborateurs[i].afficher();
//                    }
//
//                case 0:
//                    menu = false;
//                    break;
//
//            }
//
//
//        }




    }
}
