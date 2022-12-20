package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainProcess {

	public static void main(String[] args) {
		String[] params = new String [3];
	    params[0] = "/Users/migeon/workspaces/ILUProjectWorkspace/ILU1ProjectExample/ocaml/ocaml-cli/main.exe";
	    params[1] = "10";
	    params[2] = "20";

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
