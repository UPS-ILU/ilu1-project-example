package main;

import java.io.IOException;

public class MainRuntime {

	public static void main(String[] args) {
		String[] params = new String [5];
	    params[0] = "ocaml/ocaml-cli/main.exe";
	    params[1] = "sum";
	    params[2] = "--";
	    params[3] = "10";
	    params[4] = "20";
	    try {
			Runtime.getRuntime().exec(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Program executed.");
	}

}
