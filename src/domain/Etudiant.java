package domain;

import java.util.Arrays;

public class Etudiant {
	private static int MAX_NB_EPREUVES = 50;
	private static int MAX_NB_MODULES = 50;
	
	private final String ine;
	private final String  nom;
	private final String prenom;
	private boolean beneficeTiersTemps;

	private Epreuve [] epreuves;
	private Enseignement [] enseignements;
	
	public Etudiant(String ine, String nom, String prenom, boolean beneficeTiersTemps) {
		super();
		this.ine = ine;
		this.nom = nom;
		this.prenom = prenom;
		this.beneficeTiersTemps = beneficeTiersTemps;
		
		assert estIdentifie();
	}

	public String getIne() {
		return ine;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public boolean beneficieTiersTemps() {
		return beneficeTiersTemps;
	}

	public Epreuve[] getEpreuves() {
		return epreuves;
	}

	public Enseignement[] getEnseignements() {
		return enseignements;
	}

	public void changeBeneficeTiersTemps(boolean beneficeTiersTemps) {
		this.beneficeTiersTemps = beneficeTiersTemps;
	}

	public void passeEpreuves(Epreuve[] epreuves) {
		this.epreuves = Arrays.copyOfRange(epreuves, 0, MAX_NB_EPREUVES);
	}

	public void setEnseignements(Enseignement[] enseignements) {
		this.enseignements = Arrays.copyOfRange(enseignements, 0, MAX_NB_MODULES);
	}
	
	private boolean estIdentifie() {
		return (ine != null) && (nom != null) && (!nom.isBlank()) && (prenom != null) && (!prenom.isBlank());
	}
	
	private boolean estInscrit() {
		return (epreuves!=null) && (enseignements != null) && (epreuves[0] != null) && (enseignements[0] != null);
	}
	
	
}
