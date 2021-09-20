


## Wellcome
Welcome est une application mobile qui se base sur le principe la rencontre entre les particuliers
pour êtres logés gratuitement.Elle servira aussi à proposer des services basique comme servir un verre
d'eau.Ou inversement a déposé une annonce de nos besoins.
Les utilisateurs devront se connecter via un compte Wellcome. Au départ les utilisateurs auront un
compte non certifié. Les comptes non certifiés  ne pourraient pas communiquer avec les autres participants.
Pour enlever le mode restreint les utilisateurs devront envoyer leur carte d’identité qui sera validé par l’administrateur et stocker.

__Outil de développement et choix technologique :__

Pour le développement de notre application nous avons choisie d'utiliser android car il y a beaucoup de ressources et documentation sur internet et c’est le logiciel adéquat pour programmer cette application mobile. La version d’api 28 est la plus récente, la plus performante et la plus stable.
On avait le choix entre java et kotlin. Notre choix s'est porté naturellement vers kotlin car sa requiers moins de code que java, 70% des applications mobile sont construit via kotlin.  
Pour pouvoir avoir des statistiques d’analyse pertinente sur la qualité du code source nous avons choisis sonarqube.
Pour le serveur rest service on a utilisé la technologie web api de dotNet. Étant donné c’est performance et la documentation claire et concise ce qui fait de lui un langage moderne permettant d'écrire du code claire et simple.


__1. Module Recherche Logement :__
<br/>
Quand le client cliquera sur le module -> Formulaire pour choisir la destination + Date + Nombre de personne (information age)
Ensuite une liste d'annonces avec page de filtres d’autre particulière qui offre le service de logement.
Photos
Adresse
Type de logement(une chambre/une appartement )
Système de messageries pour les utilisateurs
pour faciliter la partage d’annonce et poser des questions aux propriétaires
Publier les annonces
fonctionnement de notre site
Si il n'y a pas d’annonce l'utilisateur pourras déposer une annonce de demande + système d’email pour les particuliers ayant accepté les notifications et habitant dans la zone
Système de pourboire facultatif

Pour les voyageurs qui se situent dans une ville et qui voudraient camper quelque part, l’application leur montrera l’endroit le plus proche ou il pourrait passer la nuit.



__2. Chat avec propriétaire :__
<br/>
Il permet de communiquer avec les propriétaires et d’avoir une notification quand il reçoit un message du propriétaire.

__3. Gestion de profile :__
<br/>
côté client :
Modifier les informations personnelles
Consulter les voyages historiques
Bouton de déconnection
côté propriétaire :
Bouton pour accéder l’article “comment devenir le propriétaire”
Proposer les annonces de logement
Gérer mes annonces de logement
côté application:
Bouton pour accéder l’article “description de notre application”

## Installation
### Récuperation du code source
> git clone https://github.com/JialeiSUN/Wellcome

### Installez gradle 
__Lien vers Gradle : https://gradle.org/install/__

> gradle build

### Installez ADB : https://www.xda-developers.com/install-adb-windows-macos-linux/
> adb -d install path/to/your_app.apk

## Liste de tâches Feature V0.1
- [ ] Localisation GPS : __Jialei__
- [ ] Base de données SQLite : __Rayhane__
- [ ] Récuperer et afficher les logements : __Ahmed__
- [ ] Formulaire ajouter logement : __Amadou__

*Pour mardi:
<br/>
__Localisation Gps__ : Jialei
Créer une classe outil permettant via une entrée(adresse) nous donne une liste de logement proche de cette adresse.
<br/>
__Data Sql light :__ Rayahne
<br/>
__Page liste de logement :__ Ahmed Benali
<br/>
__Formulaire ajouter logement :__ Amadou
Nom prénom e-mail adresse.*



