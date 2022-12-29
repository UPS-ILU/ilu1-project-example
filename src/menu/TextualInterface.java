package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextualInterface {

	private Controller control;	

	public TextualInterface(Controller control) {
		super();
		this.control = control;
	}

	/**
	 * Prints the main menu and handles user input for main menu commands.
	 */
	public void mainMenu() {
		System.out.println("1. Lire les données de la base");
		System.out.println("2. Travailler sur les étudiants (consulter, modifier, supprimer)");
		System.out.println("3. Travailler sur les épreuves (consulter, modifier, supprimer)");
		System.out.println("4. Travailler sur les modules (consulter, modifier, supprimer)");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("0. Exit\n");

		// Get user input
		try {
			int userInput = Integer
					.parseInt(inputOutput("Veuillez entrer un chiffre correspondant à la fonctionnalité désirée."));

			if (userInput >= 0 && userInput <= 6) {
				switch (userInput) {
				case 0:
					enregistrerDonnees();
					System.exit(0);
					break;
				case 1:
					lireBaseDonnees();
					break;
				}
			} else {
				System.out.println("Veuillez entrer un chiffre entre 0 et 6.\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("Cette entrée ne correspond pas à un chiffre.\n");
		}
	}

	/**
	 * Lit l'intégralité de la base de données et initialise l'application avec les
	 * données lues.
	 */
	public void lireBaseDonnees() {
		control.lireBaseDonnees();
	}
	
	/**
	 * Enregistre l'intégralité des données du domaine métier en "base de données".
	 */
	public void enregistrerDonnees() {
		control.enregistrerDonnes();
	}

	/**
	 * Passes a prompt to the user and returns the user specified string.
	 * 
	 * @param message
	 * @return String
	 */
	private String inputOutput(String message) {
		System.out.println(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String returnString = "";
		try {
			returnString = br.readLine();
		} catch (IOException e) {
			System.out.println("Error reading in value");
			mainMenu();
		}
		return returnString;
	}

}
