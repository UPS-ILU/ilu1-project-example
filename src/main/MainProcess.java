package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainProcess {

	private static final String CRUD_ARGS = "persist -C 1 Migeon Frederic False 0 0 -- etudiants.csv";
	private static final String OCAML_CLI_DIR = "ocaml-cli";
	private static final String OCAML_DIR = "ocaml";

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		String ocamlCLIpath = projectPath + File.separator + OCAML_DIR + File.separator + OCAML_CLI_DIR;
		System.out.println("Path = " + ocamlCLIpath);

		String []  params = CRUD_ARGS.split(" ");
	    params[0] = ocamlCLIpath + File.separator + params[0];

		Process process;
		try {
			process = new ProcessBuilder(params).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.printf("Output of running %s is :\n", Arrays.toString(params));
		int i =  1;
		while ((line = br.readLine()) != null) {
		  System.out.println((i++) + " : "+line);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
