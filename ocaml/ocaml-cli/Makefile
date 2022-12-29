SRC_OCAML = parse_cli.ml libcsv.ml libunix.ml sum_avg.ml main.ml
#SRC_OCAML = parse_cli.ml libcsv.ml libunix.ml crud_csv.ml mainPersistance.ml

SOURCES = $(addprefix src/,$(SRC_OCAML))
PACKS = csv unix
RESULT = main.exe
#RESULT = persist
all: byte-code

-include OCamlMakefile
