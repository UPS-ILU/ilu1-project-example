package domain;

import java.util.Arrays;
import java.util.Objects;

public class Etudiant {
	private static int MAX_NB_EPREUVES = 50;
	private static int MAX_NB_MODULES = 50;
	
	private final String ine;
	private final String  nom;
	private final String prenom;
	private boolean beneficeTiersTemps;

	private Epreuve [] epreuves = new Epreuve[MAX_NB_EPREUVES];
	private Enseignement [] enseignements = new Enseignement[MAX_NB_MODULES];
	
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

	@Override
	public String toString() {
		return "Etudiant [ine=" + ine + ", nom=" + nom + ", prenom=" + prenom + ", beneficeTiersTemps="
				+ beneficeTiersTemps + ", convocations= " + getNomsConvocations() 
				+ ", inscriptions=" + getTitreInscriptions() + "]";
	}
	
	private String getTitreInscriptions() {
		int i;
		StringBuffer result = new StringBuffer();
		for(i=0; i<MAX_NB_MODULES && enseignements[i]!=null;i++) {
			result.append(enseignements[i].getTitre()+" ");
		}
		return result.toString();
	}

	private String getNomsConvocations() {
		int i;
		StringBuffer result= new StringBuffer();
		for(i=0; i<MAX_NB_EPREUVES && epreuves[i]!=null;i++) {
			result.append(epreuves[i].getNom()+" ");
		}
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return Objects.equals(ine, other.ine);
	}

	public void addInscription(Enseignement moduleInscrit) {
		int i;
		for(i=0; i<MAX_NB_MODULES && enseignements[i]!=null && !enseignements[i].equals(moduleInscrit);i++);
		if (i<MAX_NB_MODULES) {
			enseignements[i]=moduleInscrit;
		}
	}
	
	public void addEpreuve(Epreuve convocation) {
		int i;
		for(i=0; i<MAX_NB_EPREUVES && epreuves[i]!=null && !epreuves[i].equals(convocation);i++);
		if (i<MAX_NB_EPREUVES) {
			epreuves[i]=convocation;
		}
	}

	
}
