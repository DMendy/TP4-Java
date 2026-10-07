public class Testeur extends Collaborateur {

    public Testeur(String nom, String prenom, String langagePrefere, double salaire, String rue, int codePostal, String ville, String pays) {
        super(nom, prenom, langagePrefere, salaire, rue, codePostal, ville, pays);
    }


    @Override
    public void travailler() {
        System.out.println(prenom + " exécute une campagne de tests.");
    }

    public static Testeur creerTesteur(String nom, String prenom, String langagePrefere,
                                       double salaire, String rue, int codePostal, String ville, String pays) {
        return new Testeur(nom, prenom, langagePrefere, salaire ,rue,codePostal,ville, pays);
    }

}
