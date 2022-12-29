(* les chemins utilisés font l'hypothèse que le projet est organisé   *)
(* sous forme de projet Java en Eclipse (avec repertoire src et bin   *)
(* auquel est rajouté un répertoire ocaml au meme niveau que src.     *)
(* Le repertoire ocaml contient lui-même un sous-repertoire ocaml-cli *)
(* qui contient lui-même un repertoire src pour les sources .ml et le *)
(* répertoire data contenant les fichiers csv.                        *)
(* Les fichiers doivent être créés (vides au minimum).                *)
let data_directory = "data"

(* Concevez avec beaucoup d'attention la structure de vos données     *)


(* --------- CREATE ----------------*)
let create_fn arg_list csvname =
	...

(* --------- READ ----------------*)
let read_fn arg_list csvname =
  ...
		
(* --------- UPDATE ----------------*)	
let update_fn arg_list csvname =
	...

(* --------- DELETE ----------------*)
let delete_fn arg_list csvname = 
	...
	




(* Exemples de tests unitaires de la commande de persistance sur *)
(* un domaine gérant des étudiants, epreuves, modules...         *)
(*
./persist -C 1 Migeon Frederic False -- etudiants.csv
./persist -C 1 CC1 1 KINXIL11 -- epreuves.csv
./persist -C 1 ILU1 6 -- modules.csv

./persist -R -all -- etudiants.csv
./persist -R -first Nom Migeon -- etudiants.csv
./persist -R -last Nom Migeon -- etudiants.csv

./persist -U -all Nom Migeon Migeo -- etudiants.csv
./persist -U -first Nom Migeo Migeon -- etudiants.csv
./persist -U -last Nom Migeo Migeon -- etudiants.csv

./persist -D -all Nom Migeon -- etudiants.csv
./persist -D -first Nom Migeon -- etudiants.csv
./persist -D -last Nom Migeon -- etudiants.csv

*)
