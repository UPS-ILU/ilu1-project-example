# Projet ILU1

### Couplage Java - OCaml

Une façon simple (mais pas tout à fait réaliste) d'obtenir la persistance et de coupler la partie Java et Ocaml est de lire l'ensemble des données stockées dans les fichiers `csv` en début de programme (ou de proposer cela comme opération du menu) et de stocker à nouveau toutes les informations contenues dans les objets du domaine métier juste avant que le programme se termine.
* Pour cela, un moyen peut être d'écrire un programme OCaml qui se charge de la persistance. Dans l'exemple de projet proposé (https://github.com/UPS-ILU/ilu1-project-example), ce programme a été nommé `persist` (valeur de `RESULT` à décommenter dans le `Makefile`). Le choix a également été fait d'utiliser une option (`-C`, `-R`, `-U` ou `-D`) pour l'opération CRUD visée et les valeurs optionnelles de l'opération avant le marqueur de fin d'option (`--`). Enfin, le nom de fichier utilisé cible quelles sont les données affectées par l'opération CRUD. Ces éléments sont des choix proposés ; vous pouvez choisir d'autres conventions.
* Étant donnés ces choix, un fichier `crud_csv.ml` peut être défini qui précise les implantations des fonctions CRUD.
* Ainsi fait, la classe Java `DataAccessObject`  qui s'occupe d'appeler le programme Ocaml pour la persistance définit la commande à appeler et adapte l'exemple de `MainProcess.java` pour appeler et récupérer les résultats des opérations CRUD.

