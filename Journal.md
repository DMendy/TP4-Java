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

## Etape 4 : Debugger et logs

* Ajout des dépendances slf4j-api et slf4j-simple dans le pom.xml
* Ajout de logs dans les classes qui ne parlent pas à l'utilisateur :
  * Annuaire : logger.info quand un collaborateur est ajouté, logger.warn quand un doublon est refusé
  * Collaborateur : logger.debug pour voir le salaire avant / après une augmentation
* Les System.out.println du menu restent des messages pour l'utilisateur, les logs sont pour le développeur
* Pour voir les logs DEBUG il faut lancer avec -Dorg.slf4j.simpleLogger.defaultLogLevel=debug

Corrections faites ensuite :
* L'exception garde maintenant l'identifiant fautif avec la méthode identifiant()
* Suppression du logger.error dans le menu : un doublon n'est pas une erreur et il est déjà logué en warn dans Annuaire
* augmentation() lance une IllegalArgumentException au lieu d'afficher "Salaire négatif"
* Correction des saisies au Scanner (ajout d'un nextLine() après nextInt() / nextDouble(), sinon la saisie suivante était vide)
* Suppression des méthodes statiques d'Annuaire qui plantaient (elles seront remplacées par des requêtes JPQL)

## Etape 5 : Persistance avec JPA

* Démarrage de MySQL dans XAMPP et import de tp4-collaborateurs.sql dans phpMyAdmin : la base efrei_tp4 contient collaborateur_demo avec 6 lignes
* Ajout de hibernate-core et mariadb-java-client dans le pom.xml
* Création de src/main/resources/META-INF/persistence.xml avec la persistence unit collaborateurs-pu et nos 3 entités
* Annotations sur Collaborateur :
  * @Entity pour dire que la classe correspond à une table
  * @Id sur l'identifiant (C001 sert de clé primaire pour l'instant)
  * @Column pour préciser la longueur et si la colonne peut être vide (nom et salaire obligatoires)
  * @Transient sur l'adresse, on la persistera à l'étape 9
  * Un constructeur protected sans argument, obligatoire pour JPA (aussi dans Programmeur et Testeur)

Choix de l'héritage : une seule table pour toute la hiérarchie (SINGLE_TABLE)
* Une seule table Collaborateur avec une colonne metier qui dit si c'est un Programmeur ou un Testeur (@DiscriminatorColumn / @DiscriminatorValue)
* Programmeur et Testeur n'ont pas d'attributs en plus, donc une table par classe n'apporte rien
* Pour chercher les collaborateurs dont le salaire dépasse un seuil (étape 8), il suffit d'un SELECT sur une seule table, sans jointure (JOINED) ni UNION (TABLE_PER_CLASS)
* Inconvénient : les colonnes propres à un seul type doivent pouvoir être NULL (ex : langagePrefere pour un testeur)

Objet Java / entité / ligne :
* Un objet Java existe en mémoire, il disparaît quand l'application s'arrête
* Une entité est une classe annotée @Entity que JPA sait relier à une table
* Une ligne est la donnée enregistrée dans la table, elle reste après l'arrêt de l'application

Premier contact (classe PremierContact) :
* On ouvre puis on ferme l'EntityManagerFactory : Hibernate crée la table Collaborateur (on voit le create table dans la console)
* Test avec MySQL arrêté : grosse stack trace avec "Connection refused"
  * Il faut lire le dernier "Caused by" pour trouver la vraie cause : la base n'est pas démarrée
  * Ce message est pour le développeur, pas pour l'utilisateur. Pour l'utilisateur il faudrait un message simple comme "Impossible de se connecter à la base de données"

## Etape 6 : Alice entre dans la base

* Création de la classe CollaborateurService qui contient tout le code JPA (le menu ne fait que dialoguer avec l'utilisateur)
  * Elle reçoit l'EntityManagerFactory dans son constructeur
  * ajouter() : dans une transaction, find pour vérifier le doublon, puis persist et commit. En cas d'erreur on fait un rollback
  * trouver() : un simple em.find(), pas besoin de transaction pour une lecture
  * nombre() : compte les collaborateurs en base
* Dans MenuAnnuaire :
  * L'EntityManagerFactory est créée une seule fois au démarrage (elle est coûteuse) et fermée quand on quitte
  * Un EntityManager est créé pour chaque opération puis fermé
  * Ajout d'une option pour rechercher un collaborateur par identifiant
  * La Map d'Annuaire n'est plus utilisée : c'est maintenant la base qui contient les données

Tests :
* On ajoute Alice (C001), on quitte, on relance : on la retrouve avec find, elle n'a pas disparu
* On ajoute deux fois C001 : le doublon est refusé avec notre exception et un message clair
* find avec un identifiant qui n'existe pas (C999) renvoie null

Règle du doublon :
* La clé primaire empêche bien les doublons en base, mais l'erreur arrive seulement au commit sous forme d'erreur SQL, ce qui n'est pas compréhensible pour l'utilisateur
* C'est pour ça qu'on fait un find avant le persist : on peut lancer notre CollaborateurDejaExistantException avec un message adapté

Limites d'une clé comme C001 :
* Le format "C + 3 chiffres" limite à 999 collaborateurs, à C1000 le format ne tient plus
* C'est à nous de choisir le prochain identifiant : si deux utilisateurs créent C007 en même temps, un des deux aura une erreur
* Si on se trompe (C01 au lieu de C001), il faut corriger la clé partout où elle est utilisée (par exemple dans la table adresse plus tard)
* Une clé plus propre serait un identifiant sans signification, fabriqué automatiquement par la base
