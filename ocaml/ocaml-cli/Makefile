SRC_OCAML = parse_cli.ml libcsv.ml libunix.ml crud_csv.ml mainPersistance.ml

SOURCES = $(addprefix src/,$(SRC_OCAML))
PACKS = csv unix
RESULT = persist
all: byte-code

-include OCamlMakefile
