package menu;

import dao.DataAccessObject;

public class Controller {

	// définition d'attributs supplémentaires si nécessaire
	// pour stocker les objets initialisés depuis les fichiers csv
	
	private DataAccessObject dao;
	
	public Controller (DataAccessObject dao) {
		this.dao = dao;
	}
	
	public void lireBaseDonnees() {		
		// utilisation de méthodes de l'objet dao pour lire les données
	}

	public void enregistrerDonnees() {
		// utilisation de méthodes de l'objet dao pour enregistrer les données
	}

}
