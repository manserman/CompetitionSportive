Description
Ce projet simule le fonctionnement d'une compétition sportive en Java. Il met en œuvre plusieurs design patterns pour structurer le code de manière efficace et extensible. Les patterns utilisés incluent Builder, Factory, Façade et Composite pour la création, la gestion et l'organisation des différentes composantes de la compétition.

Fonctionnalités
Simulation complète d'une compétition sportive avec gestion des participants, des matchs, et des classements.
Design Patterns Utilisés :
Builder : Crée des objets complexes de manière progressive et personnalisée, comme les compétitions ou les équipes.
Factory : Fournit un moyen flexible de créer des objets spécifiques selon leurs types, par exemple des différents types de participants.
Façade : Simplifie l'interface d'utilisation du système en regroupant les interactions complexes.
Composite : Permet de traiter de manière uniforme des objets simples (participants) et des objets composés (équipes).
Organisation modulaire pour une extension et une maintenance aisées.
Prérequis
Java 11 ou version ultérieure.
IDE tel que IntelliJ IDEA, Eclipse ou tout autre éditeur de texte compatible Java.
Installation
Clonez ce dépôt :

bash
Copier le code
git clone [https://github.com/username/sports-competition-simulation.git](https://github.com/manserman/CompetitionSportive/)
Naviguez dans le répertoire du projet :

bash
Copier le code
cd sports-competition-simulation
Compilez et exécutez le projet à l'aide de votre IDE préféré ou de la ligne de commande :

bash
Copier le code
javac -d bin src/*.java
java -cp bin Main
Structure du Projet
src/ : Contient les fichiers sources du projet.
model/ : Définit les entités de la compétition (participants, équipes, matchs) et la logique de simulation.
patterns/ : Implémente les design patterns utilisés (Builder, Factory, Façade, Composite).
controller/ : Gère les interactions avec l'utilisateur et la logique de coordination de la compétition.
view/ : Affiche les informations liées à la compétition.
Utilisation
Le programme permet de configurer une compétition sportive à l'aide du Builder Pattern pour personnaliser ses paramètres (nombre d'équipes, règles, etc.).
Des objets tels que des participants et des équipes sont créés dynamiquement à l'aide du Factory Pattern.
Le Façade Pattern offre une interface simplifiée pour lancer et gérer la compétition.
La structure hiérarchique de la compétition (ex. équipes et joueurs) est gérée à l'aide du Composite Pattern.
Améliorations Futures
Ajout de différents types de compétitions (tournois, ligues, etc.).
Gestion des scores en temps réel.
Interface graphique pour configurer et suivre la compétition.
Extension des règles et des types de participants.
