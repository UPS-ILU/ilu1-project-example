package domain;

public class Epreuve {
	private static int MAX_NB_CANDIDATS = 500;

	private String nom;
	private Etudiant[] candidats;
	private int nbCandidats = 0;
	private Enseignement enseignement;

	public Epreuve(String nom, Enseignement enseignement) {
		super();
		this.nom = nom;
		this.enseignement = enseignement;

		assert estDefinie();
	}

	public String getNom() {
		return nom;
	}

	public Etudiant[] getCandidats() {
		return candidats;
	}

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public void inscrirePromo(Etudiant[] candidats) {
		candidats = new Etudiant[MAX_NB_CANDIDATS];
		nbCandidats = 0;
		for (int i = 0; i < Math.min(MAX_NB_CANDIDATS, candidats.length)
				&& (candidats[i] != null); i++, nbCandidats++) {
			this.candidats[i] = candidats[i];
		}
	}

	public void ajouterCandidat(Etudiant candidat) {
		if (nbCandidats < MAX_NB_CANDIDATS) {
			candidats[nbCandidats++] = candidat;
		}
	}

	private boolean estDefinie() {
		return (nom != null) && (!nom.isBlank()) && (enseignement != null);
	}

}
