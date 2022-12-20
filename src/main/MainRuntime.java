package main;

import java.io.IOException;

public class MainRuntime {

	public static void main(String[] args) {
		String[] params = new String [3];
	    params[0] = "/Users/migeon/cloudIrit/Enseignement/Accreditation2021_2026/LFLEX/ILU1/Projet/POC/ups-ilu1-ocaml-examples/ocaml-cli/main.exe";
	    params[1] = "10";
	    params[2] = "20";
	    try {
			Runtime.getRuntime().exec(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Program executed.");
	}

}
