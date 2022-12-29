(* Définition des différentes commandes et de leurs activations *)
let main_persistance () =

	(* les options permettent de spécifier quelle opération CRUD est ciblée :                       *)
	(*  -C pour Create                                                                              *)
	(*  -R pour Read                                                                                *)
	(*  -U pour Update                                                                              *)
	(*  -D pour Delete                                                                              *)
	(*                                                                                              *)
	(* les arguments permettent de spécifier le fichier csv contenant les données à modifier        *)
	(*                                                                                              *)
	(* Exemples d'usage                                                                             *)
	(*  persist -C 1 CC1 0 KINXIL11 -- epreuves.csv                                                 *)
	(*  persist -R -all -- etudiants.csv                                                            *)
	(*  persist -U -first Nom Migeo Migeon -- etudiants.csv                                         *)
	(*  persist -D -last Nom Migeon -- etudiants.csv                                                *)
	
  let prog, options, args = Parse_cli.get_args () in

		(* Hypothese : un seul nom de fichier est fourni dans la partie args                          *)
  	let filename = (String.concat " " args) in
		(* Hypothese : options = <op_CRUD> :: <args_CRUD>                                             *)
		let crud_option = (String.sub (List.hd options) 1 1) in
		(* Hypothese : crud_args ne contient pas d'erreur d'usage                                     *)
		let crud_args = (List.tl options) in
		let result = match crud_option with
			| "C" -> Crud_csv.create_fn crud_args filename
			| "R" -> Crud_csv.read_fn crud_args filename
			| "U" -> Crud_csv.update_fn crud_args filename
			| "D" -> Crud_csv.delete_fn crud_args filename
			| _ -> failwith "wrong CRUD option" in
(*		print_endline (filename ^ "\nCRUD op = " ^ crud_option ^ "\nCRUD args = " ^
									 (String.concat " " crud_args) ^ "\nResult = " ^ (String.concat " " (List.flatten result))) *)
		print_endline (String.concat "\n" (List.map (String.concat " ") result))
		
let () = main_persistance ()
		