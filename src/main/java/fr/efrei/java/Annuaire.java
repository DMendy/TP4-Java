package fr.efrei.java;

import java.util.*;

public class Annuaire {

        private static Map<String, Collaborateur> collaborateurs = new HashMap<>();

        public boolean ajouter(Collaborateur collaborateur) {
            if (collaborateurs.containsKey(collaborateur.getIdentifiant())) {
                throw new CollaborateurDejaExistantException(collaborateur.getIdentifiant());
            }
            collaborateurs.put(collaborateur.getIdentifiant(), collaborateur);
            return true;
        }

        public Collaborateur trouver(String identifiant) {
            return collaborateurs.get(identifiant);
        }

        public boolean supprimer(String identifiant) {
            return collaborateurs.remove(identifiant) != null;
        }

        public int taille() {
            return collaborateurs.size();
        }

        public List<Collaborateur> triesParNom() {
            List<Collaborateur> liste = new ArrayList<>(collaborateurs.values());
            for (int i = 0; i < liste.size() - 1; i++) {
                for (int j = 0; j < liste.size() - 1 - i; j++) {
                    if (liste.get(j).getNom().compareTo(liste.get(j + 1).getNom()) > 0) {
                        Collaborateur temporaire = liste.get(j);
                        liste.set(j, liste.get(j + 1));
                        liste.set(j + 1, temporaire);
                    }
                }
            }
            return Collections.unmodifiableList(liste);
        }

        public List<Collaborateur> triesParSalaire() {
            List<Collaborateur> liste = new ArrayList<>(collaborateurs.values());
            for (int i = 0; i < liste.size() - 1; i++) {
                for (int j = 0; j < liste.size() - 1 - i; j++) {
                    if (Double.compare(liste.get(j).getSalaire(), liste.get(j + 1).getSalaire()) > 0) {
                        Collaborateur temporaire = liste.get(j);
                        liste.set(j, liste.get(j + 1));
                        liste.set(j + 1, temporaire);
                    }
                }
            }
            return Collections.unmodifiableList(liste);
        }

        public List<Programmeur> programmeurs() {
            List<Programmeur> liste = new ArrayList<>();
            for (Collaborateur c : collaborateurs.values()) {
                if (c instanceof Programmeur programmeur) {
                    liste.add(programmeur);
                }
            }
            return liste;
        }

        public List<fr.efrei.java.Testeur> testeur() {
            List<fr.efrei.java.Testeur> liste = new ArrayList<>();
            for (Collaborateur c : collaborateurs.values()) {
                if (c instanceof Testeur testeur) {
                    liste.add(testeur);
                }
            }
            return liste;
        }

        public List<Collaborateur> salaireSuperieurA(double seuil) {
            List<Collaborateur> liste = new ArrayList<>();
            for (Collaborateur c : collaborateurs.values()) {
                if (c.getSalaire() > seuil) {
                    liste.add(c);
                }
            }
            return liste;
        }

    public static void chercherCollaborateurById(String identifiant) {
        for (int i = 0; i < collaborateurs.size(); i++) {
            if (collaborateurs.get(i).getIdentifiant().equals(identifiant)) {
                System.out.println(collaborateurs.get(i));
            }
        }
    }


    public static void chercherCollaborateurByName(String nom) {
        for (int i = 0; i < collaborateurs.size(); i++) {
            if (collaborateurs.get(i).getNom().equals(nom) & !collaborateurs.get(i).getNom().isBlank() ) {
                System.out.println(collaborateurs.get(i));
            }
        }
    }

    public static void afficherCollaborateurSeuil(int seuil) {
        for (int i = 0; i < collaborateurs.size(); i++) {
            if (collaborateurs.get(i).getSalaire() > seuil ) {
                System.out.println(collaborateurs.get(i));
            }
        }
    }




}
