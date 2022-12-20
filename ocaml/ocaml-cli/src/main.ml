(* Définit une première procédure - programme lisant la ligne de commande *)
let main_sumavg () =

  let prog, options, args = Parse_cli.get_args () in

  (* s'il y avait un "--" dans les arguments en ligne de commande,
     ignore le découpage fait entre l1 et l2, et recolle le tout *)
  (*let all = options @ args in *)

  (* on suppose que tous les arguments sont bien des entiers et donc qu'aucune exception n'est levée *)
  let list_of_numbers = List.map int_of_string args in

  let sum, avg = Sum_avg.sum_avg list_of_numbers in

  (* un exemple de texte à afficher *)
  let text = "options = " ^ (String.concat " " options) ^ "\nsum = " ^ string_of_int sum ^ "\n"
             ^ "avg = " ^ Format.sprintf "%.2f" avg in

  print_endline text

(* Définit une autre procédure - programme lisant/écrivant un CSV *)
let main_csv_demo () =
  let chemin = Libunix.get_example_file "musee.csv" in
  let output = Libunix.get_example_file "musee_output.csv" in
  let csv = Libcsv.load_csv chemin in
  let csv' = Libcsv.map_csv (fun s -> "Super-" ^ s) csv in
  let nl, nc = Libcsv.lines_columns csv' in
  let () = Format.printf "Ecriture d'un CSV de taille (%d x %d) dans: %s\n" nl nc output in
  Libcsv.save_csv output csv'

(* Exécute les procédures précédentes *)

(*let () = main_sumavg ()

let () = main_csv_demo () *)


(* Définit une procédure qui réagit aux options positionnées :                                      *)
(* main.exe sum -- 10 20                              -> calcule somme et moyenne                   *)
(* main.exe csv -- 10 20                              -> qui lit et ecrit un csv                    *)
(* main.exe -option opt1 -option2 opt2 -- 10 20       -> affiche "The very best is comming"         *)

let main_with_options () =
	let prog, options, args = Parse_cli.get_args () in
  let list_of_numbers = List.map int_of_string args in
	let sum, avg = Sum_avg.sum_avg list_of_numbers in
	let text = "options = " ^ (String.concat " " options) ^ "\nsum = " ^ string_of_int sum ^ "\n" ^ "avg = " ^ Format.sprintf "%.2f" avg in

  let chemin = Libunix.get_example_file "musee.csv" in
  let output = Libunix.get_example_file "musee_output.csv" in
  let csv = Libcsv.load_csv chemin in
  let csv' = Libcsv.map_csv (fun s -> "Super-" ^ s) csv in
  let nl, nc = Libcsv.lines_columns csv' in
	let make_csv_demo () = let () = Format.printf "Ecriture d'un CSV de taille (%d x %d) dans: %s\n" nl nc output in Libcsv.save_csv output csv' in	
		if (List.mem "sum" options) then print_endline text
		else if (List.mem "csv" options) then make_csv_demo ()
				else print_endline "The very best is coming"


let () = main_with_options ()