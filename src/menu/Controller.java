package menu;

import dao.DataAccessObject;
import domain.Enseignement;
import domain.Epreuve;
import domain.Etudiant;

public class Controller {

	private Etudiant[] lesEtudiants;
	private Epreuve[] lesEpreuves;
	private Enseignement[] lesModules;
	
	private DataAccessObject dao;
	
	public Controller (DataAccessObject dao) {
		this.dao = dao;
	}
	
	public void lireBaseDonnees() {
		lesEtudiants = dao.chargeTousLesEtudiants();
		lesModules = dao.chargeTousLesEnseignements();
		lesEpreuves = dao.chargeToutesLesEpreuvesEtRelieLesModules(lesModules);
		dao.chargeLesInscriptions(lesEtudiants, lesModules);
		dao.chargeLesConvocations(lesEtudiants,lesEpreuves, lesModules);
		
	}

	public void enregistrerDonnes() {
		dao.enregistreLesEtudiants(lesEtudiants);
		dao.enregistreLesModules(lesModules);
		dao.enregistreLesEpreuves(lesEpreuves);
	}

}
