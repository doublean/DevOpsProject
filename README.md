
Pour constuire et compiler le projet : mvn package
Pour lancer les test : mvn test
Pour exécuter : java -cp target/Pandas-1.0-SNAPSHOT.jar Project.Devops.Sec.App


# DEVOPS project

AMAL Mouataz : mouataz.amal@gmail.com  
ANASSE anouar : anasse.anouar@gmail.com  
BELHADJI Anis : anis_belahadji@yahoo.fr  

Underground Resistance est une bibliothèque que nous avons réalisé et qui fournit de multiples services :

# Fonctionnalités offerte par notre bibliothéque

 1. Underground Resistance permet de lire des données via un tableau donné en paramétre ou via un fichier CSV.
 2. Permet d’afficher à la console le contenu de la base de données, ou encore afficher que la première ou dernière partie de la dataframe.
 3. Sélectionner une partie des data frames en fonction du besoin de l’utilisateur en utilisant plusieurs type d’entrée : la ligne souhaité, les lignes entre deux bornes, des lignes précises ou encore avec les labels des colonnes ou encore avec des valeurs à vérifier ou avec plusieurs autres critères .
 4. Une sélection de haut niveau en donnant une valeur à  la bibliothèque puis le souhait de l’utilisateur d’avoir les valeurs égal, supérieur ou inférieur.
 5. Des services de statistiques sont fournies également comme la moyenne des le maximum, le minimum .
 6. Underground resistance fournit également des services d'agrégations précédé par un regroupement.


# outils utilisés :

IDE : Intellij
Intégration continu : TravisCI
Automatisation de la production du logiciel : Maven
Gestion de versionning : Git/Github
Test : JUnit (Nous avons utilisé un outil intégré à intellij pour Le rapport de couverture du code)
Conteneur : Docker


#FeedBack :
Notre expérience avec maven est très positive, car cet outil propose une solution à pleins de problémes que nous avions confronté auparavant de mise en place d’un projet
De même pour l’outil TravisCi qui qui nous a beaucoup aidé à voir si l’intégration de notre code est réussi à chaque mise à jour.
Git et github est un outil indispensable pour les projet réalisé à plusieurs, et le projet De l’ue devops nous confirme cette idée.

Après notre expérience avec Docker, à notre avis, cet outil est très efficace pour les projet à grande ampleur visant à être commercialisé pour mettre en place tout l'environnement pour exécuter le logiciel réalisé. Cependant c’est indispensable de connaître cette outil avant d'être à l’écoute du marché actuel car comme dit précédemment cet outil est indispensable pour les projet à grande ampleur.


Pour conclure ses outils nous ont permis de faciliter les tâches auxiliaires à l’implémentation et nous ont permis de se concentrer d’avantages sur le côté technique du projet.



#NB :
Couverture du code :  
1.La classe App n'est pas couverte car cette dernière représente le main de notre application, et nous l'avons pas utilisé.  
2. Les exceptions lancé lors de l'éxécution des test sont des exceptions attendu car nous testons des cas où l'application devrait refusé.  


