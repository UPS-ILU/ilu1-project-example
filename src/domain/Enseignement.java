package domain;

public class Enseignement {

	private final int ects;
	private final String titre;
	
	private Etudiant [] inscrits;
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
	
	
	
}
