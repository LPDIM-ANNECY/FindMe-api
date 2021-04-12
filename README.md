# FindMe-api

Cette application est l'api utilisée dans l'application mobile `FindMe`.
Elle a pour but de faire la liaison entre la base de données et notre application `FindMe`.
L'api est construite avec le framework Kotlin `Ktor`.

## Installation et mise en place

Les étapes :
- Dans un premier temps il faut cloner le projet en local sur votre machine
- Il vous faudra installer sur votre machine PostgreSQL avec pgAdmin 4 (ou autre) : https://get.enterprisedb.com/postgresql/postgresql-13.2-1-windows-x64.exe
- Suivre ce tuto d'installation : https://www.veremes.com/installation-postgresql-windows
- Lancer une `Query Tool` pour executer le contenu du fichier `dataTest.sql` se trouvant dans le code que vous avez cloné.
- Il vous faudra modifier la ligne 20 dans le fichier `Application.kt` pour mettre votre mot de passe à la place du mot de passe déjà présent
- Dans votre IDE Jetbrains, ajouter une configuration, sélectionner `Kotlin` et mettre en MainClass `fr.find.ApplicationKt`
- Vous pouvez maintenant utiliser l'api 

## Les routes

Les routes :

- `users/`
    - Permet de récupérer tous les utilisateurs
    
- `users/{id}`
    - Permet de récupérer les données d'un utilisateur avec son id
    
- `places/`
    - Permet de récupérer toutes les places
    
- `places/{id}`
    - Permet de récupérer les données d'une place avec son id 
    
- `places/users/{id}`
    - Permet de récupérer toutes les places avec l'id d'un user pour savoir s'il les a visitées
    
- `places/category/{name}`
    - Permet de récupérer les places d'une catégorie

- `categories/`
    - Permet de récupérer les catégories

- `itineraries/`
    - Permet de récupérer les itinéraires

- `itineraries/{id}`
    - Permet de récupérer les places d'un itinéraire