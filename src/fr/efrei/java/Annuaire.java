package fr.efrei.java;
import java.util.*;



public class Annuaire {

        public String identifiant;
        private static Map<String, Collaborateur> collaborateurs = new HashMap<>();

        public boolean ajouter(Collaborateur collaborateur) {
            if (collaborateurs.containsKey(collaborateur.getIdentifiant())) {
                return false;
            }
            collaborateurs.put(String.format("C%03d", collaborateurs.size() - 1), collaborateur);
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

        public List<Programmeur> programmeurs() {
            List<Programmeur> liste = new ArrayList<>();
            for (Collaborateur c : collaborateurs.values()) {
                if (c instanceof Programmeur programmeur) {
                    liste.add(programmeur);
                }
            }
            return liste;
        }

        public List<Testeur> testeur() {
            List<Testeur> liste = new ArrayList<>();
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


//        public List<Collaborateur> triesParNom() {
//            List<Collaborateur> liste = new ArrayList<>(collaborateurs.values());
//            for (int i = 0; i < liste.size() - 1; i++) {
//                for (int j = i + 1; j < liste.size(); j++) {
//                    if (liste.get(i).getNom().compareTo(liste.get(j).getNom()) > 0) {
//                        Collaborateur temporaire = liste.get(i);
//                        liste.set(i, liste.get(j));
//                        liste.set(j, temporaire);
//                    }
//                }
//            }
//            return Collections.unmodifiableList(liste);
//        }
//
//
//        public List<Collaborateur> triesParSalaire() {
//            List<Collaborateur> liste = new ArrayList<>(collaborateurs.values());
//            for (int i = 0; i < liste.size() - 1; i++) {
//                for (int j = i + 1; j < liste.size(); j++) {
//                    if (Double.compare(liste.get(i).getSalaire(), liste.get(j).getSalaire()) > 0) {
//                        Collaborateur temporaire = liste.get(i);
//                        liste.set(i, liste.get(j));
//                        liste.set(j, temporaire);
//                    }
//                }
//            }
//            return Collections.unmodifiableList(liste);
//        }



}
