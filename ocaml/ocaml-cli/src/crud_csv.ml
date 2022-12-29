(* les chemins utilisés font l'hypothèse que le projet est organisé   *)
(* sous forme de projet Java en Eclipse (avec repertoire src et bin   *)
(* auquel est rajouté un répertoire ocaml au meme niveau que src.     *)
(* Le repertoire ocaml contient lui-même un sous-repertoire ocaml-cli *)
(* qui contient lui-même un repertoire src pour les sources .ml et le *)
(* répertoire data contenant les fichiers csv.                        *)
(* Les fichiers doivent être créés (vides au minimum).                *)
let data_directory = "data"

(* La structure des tables de données est la suivante                 *)
(* Etudiant = INE, Nom, Prenom, Tiers-Temps, keyEpreuve, keyModule    *)
(* Epreuve = Key, Nom, keyEtudiant, keyModule                         *)
(* Module = Key, Nom, NbECTS, keyEtudiant, keyEreuve                  *)
let column_number column_name csvname = match csvname with
| "etudiants.csv"  -> (match column_name with
			| "INE" -> 1
			| "Nom" -> 2
			| "Prenom" -> 3
			| "Tiers-Temps" -> 4
			| _  -> failwith ("Wrong field name for " ^ csvname))
| "epreuves.csv" -> (match column_name with
			| "Key" -> 1
			| "Nom" -> 2
			| "keyModule"  -> 4
			| _  -> failwith ("Wrong field name for " ^ csvname))
| "modules.csv" -> (match column_name with
			| "Key" -> 1
			| "Nom" -> 2
			| "NbECTS" -> 3
			| _  -> failwith ("Wrong field name for " ^ csvname))
| "inscriptions.csv" -> (match column_name with
			| "INE" -> 1
			| "nomModule" -> 2
			| _  -> failwith ("Wrong field name for " ^ csvname))
| _  -> failwith "Wronf csv filename"

let match_record column value record = (List.nth record (column -1)) = value

(* crée un nouvel enregistrement de donnée dans la table ciblée       *)
(* TODO : vérfier l'unicité de la clé primaire ?                      *)
(* TODO : vérfier la non-nullité des clés étrangères ?                *)
let create_fn arg_list csvname =
	let filename = Libunix.get_data_filename data_directory csvname in
	let content = Libcsv.load_csv filename in
	let new_content = List.append content [arg_list] in
	let () = Libcsv.save_csv filename new_content in
	[] (* return [] when no error *)

let read_fn arg_list csvname =
	let filename = Libunix.get_data_filename data_directory csvname in
	let content = Libcsv.load_csv filename in
	let rec find_first col value records = match records with
	| []  ->  []
	| first_record::rest_records  -> if (match_record col value first_record)
																		then [first_record]
																		else find_first col value rest_records in
	let rec find_last col value records = match records with
	| []  ->  []
	| first_record::rest_records  -> let result = find_last col value rest_records in 
																		if (result = [])
																		then if (match_record col value first_record)
																					then [first_record]
																					else []
																		else result in
	match arg_list with 
	| ["-all"] -> content
	| ["-first"; column_name; value] -> find_first (column_number column_name csvname) value content
	| ["-last"; column_name; value]  -> find_last (column_number column_name csvname) value content
	| _ -> failwith "Wrong argument for READ operation"
	
let update_fn arg_list csvname =
	let filename = Libunix.get_data_filename data_directory csvname in
	let content = Libcsv.load_csv filename in
	let rec update_record col new_value record = match col with
	| 1  -> new_value::(List.tl record)
	| n -> (List.hd record)::(update_record (col-1) new_value (List.tl record)) in 
	let rec update_first col old_value new_value records = match records with
	| []  -> []
	| first_record::rest_records -> if (match_record col old_value first_record)
																		then (update_record col new_value first_record)::rest_records
																		else first_record::(update_first col old_value new_value rest_records) in
	let rec update_all col old_value new_value records = match records with
	| []  -> [] 
	| first_record::rest_records -> if (match_record col old_value first_record)
																		then (update_record col new_value first_record)::update_all col old_value new_value rest_records
																		else first_record::(update_all col old_value new_value rest_records) in
	let rec update_last col old_value new_value records = match records with
	| []  ->  [],false
	| first_record::rest_records  -> let result,update_done = update_last col old_value new_value rest_records in 
																		if update_done
																		then (first_record::result,true)
																		else if (match_record col old_value first_record)
																			then (update_record col new_value first_record)::rest_records,true
																			else records,false in
let new_content = match arg_list with 
	| ["-all";column_name;old_value;new_value] -> update_all (column_number column_name csvname) old_value new_value content
	| ["-first";column_name;old_value;new_value] -> update_first (column_number column_name csvname) old_value new_value content
	| ["-last";column_name;old_value;new_value] -> fst(update_last (column_number column_name csvname) old_value new_value content)
	| _ -> failwith "Wrong argument for UPDATE operation" in
	let () = Libcsv.save_csv filename new_content in
	[] (* return [] when no error *)


let delete_fn arg_list csvname = 
	let filename = Libunix.get_data_filename data_directory csvname in
	let content = Libcsv.load_csv filename in
	let rec delete_first col value records = match records with
	| []  -> []
	| first_record::rest_records -> if (match_record col value first_record)
																		then rest_records
																		else first_record::(delete_first col value rest_records) in
	let rec delete_all col value records = match records with
	| []  -> [] 
	| first_record::rest_records -> if (match_record col value first_record)
																		then delete_all col value rest_records
																		else first_record::(delete_all col value rest_records) in
	let rec delete_last col value records = match records with
	| []  ->  [],false
	| first_record::rest_records  -> let result,del_done = delete_last col value rest_records in 
																		if del_done
																		then first_record::result,true
																		else if (match_record col value first_record)
																			then rest_records,true
																			else records,false in
	let new_content = match arg_list with 
	| ["-all";column_name;value] -> delete_all (column_number column_name csvname) value content
	| ["-first";column_name;value] -> delete_first (column_number column_name csvname) value content
	| ["-last";column_name;value] -> fst(delete_last (column_number column_name csvname) value content)
	| _ -> failwith "Wrong argument for DELETE operation" in
	let () = Libcsv.save_csv filename new_content in
	[] (* return [] when no error *)
	




(* Tests *)
(*
./persist -C 1 Migeon Frederic False -- etudiants.csv
./persist -C 1 CC1 1 KINXIL11 -- epreuves.csv
./persist -C 1 ILU1 6 1 1 -- modules.csv

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
