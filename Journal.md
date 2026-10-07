# TP 4 - Java

## Étape 0 : Refactoring du projet
Nous avons commencé par mettre en commun nos deux versions du code et les comparer avec la correction fournie.
Objectif : obtenir une version unique, propre, cohérente et comprise par nous deux.

## Etape 1 : Détection des types d'erreurs

* Identification de 2 cas provoquant un comportement incorrect :
  * Saisie invalide (texte au lieu d’un nombre).
  * Identifiant collaborateur déjà utilisé.



## Etape 2 : Gestion des erreurs

* Création de la classe CollaborateurDejaExistantException :
* Hérite de RuntimeException.
* Conserve l’identifiant fautif via un accesseur.
* Dans le menu :
  * Ajout d’un try/catch spécifique pour afficher un message utilisateur clair.
  * Lorsque l'on ajoute deux fois C001 par exemple, le programme ne s'interrompt pas et un message d'erreur comprehensible est affiché 

TP4.3 : Passage du projet en Maven

* Création d'un nouveau projet Maven
* Copie des fichiers de l'ancien projet vers le nouveau projet Maven
* Configuration du pom.xml
  * Nous avons choisi la version JDK 27 car c'est celle que nous utilisions dans l'ancine projet 
  * <maven.compiler.release>27</maven.compiler.release>
