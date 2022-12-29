package domain;

import java.util.Objects;

public class Enseignement {

	private static final int MAX_NB_INSCRITS = 250;
	private final int ects;
	private final String titre;
	
	private Etudiant [] inscrits = new Etudiant[MAX_NB_INSCRITS];
	private Epreuve [] epreuves;
	
	public Enseignement(String titre, int ects) {
		super();
		this.titre = titre;
		this.ects = ects;
	}
	
	public String getTitre() {
		return titre;
	}

	public int getEcts() {
		return ects;
	}

	public Etudiant[] getInscrits() {
		return inscrits;
	}

	public Epreuve[] getEpreuves() {
		return epreuves;
	}

	public void setInscrits(Etudiant[] inscrits) {
		this.inscrits = inscrits;
	}

	public void setEpreuves(Epreuve[] epreuves) {
		this.epreuves = epreuves;
	}

	@Override
	public String toString() {
		return "Enseignement [ects=" + ects + ", titre=" + titre + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enseignement other = (Enseignement) obj;
		return ects == other.ects && Objects.equals(titre, other.titre);
	}

	public void addInscrit(Etudiant etudiantAInscrire) {		
		int i;
		for(i=0; i<MAX_NB_INSCRITS && inscrits[i]!=null && !inscrits[i].equals(etudiantAInscrire);i++);
		if (i<MAX_NB_INSCRITS) {
			inscrits[i]=etudiantAInscrire;
		}
	}
	
}
