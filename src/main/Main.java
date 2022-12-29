package main;

import java.io.File;

import dao.DataAccessObject;
import menu.Controller;
import menu.TextualInterface;

public class Main {
	private static final String OCAML_CLI_DIR = "ocaml-cli";
	private static final String OCAML_DIR = "ocaml";
	private static final String PERSISTENCE_ASSISTANT  = "persist";

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String ocamlCLIpath = projectPath + File.separator + OCAML_DIR + File.separator + OCAML_CLI_DIR;
		
		DataAccessObject dao = new DataAccessObject(ocamlCLIpath, PERSISTENCE_ASSISTANT);
		Controller control = new Controller(dao);
		TextualInterface tui = new TextualInterface(control);
		
		while(true) tui.mainMenu();
	}

}
