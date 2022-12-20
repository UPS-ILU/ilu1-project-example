(* L'analyser ("parser" in english) est conçu pour une ligne de commande ayant la forme :                               *)
(*  commande -option1 valeur1 -option2 valeur2 -option_sans_valeur1 ... derniere_valeur_ou_option -- arg1 arg2 ... argn *)

(* Délimiteur standard indiquant la fin des options et le début des arguments *)
let end_options = "--"

(* split_args: string list -> string list * string list                    *)
(* Découpe une liste de chaînes avant/après le délimiteur end_options pour *)
(* pour donner la liste des options et la liste des arguments :            *)
(*  split_args ["-o"; "file"; "--"; "hello"] = (["-o"; "file"], ["hello"]) *)
let rec split_args l = match l with
  | [] -> ([], [])
  | s :: l -> if s = end_options then ([], l)
              else let l1, l2 = split_args l in
                   s :: l1, l2

(* get_args: unit -> string * string list * string list *)
(* Analyse/Parse les arguments en ligne de commande et renvoie un tuple :*)
(*  ("name-of-program", ["--the"; "--options"], ["the"; "other"; "args"]) *)
let get_args () =
  let all = Array.to_list Sys.argv in
  match all with
  | [] -> failwith "parse_cli"
  | s0 :: l -> let l1, l2 = split_args l in
               (s0, l1, l2)
