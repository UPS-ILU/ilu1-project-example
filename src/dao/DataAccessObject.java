package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import domain.Enseignement;
import domain.Epreuve;
import domain.Etudiant;

public class DataAccessObject {

	private final String path;
	private final String operator;

	public DataAccessObject(String path, String operator) {
		super();
		this.path = path;
		this.operator = operator;
	}

	public Etudiant[] chargeTousLesEtudiants() {
		String[] result = processCmd(operator + " -R -all -- etudiants.csv");
		Etudiant[] lesEtudiants = new Etudiant[result.length - 1];
		int i = 0;
		for (String ligne : result) {
			if (i > 0) {
				String[] caracs = ligne.split(" ");
				lesEtudiants[i - 1] = new Etudiant(caracs[0], caracs[1], caracs[2], Boolean.parseBoolean(caracs[3]));
			}
			i++;
		}
		System.out.println(Arrays.toString(lesEtudiants));
		return lesEtudiants;
	}

	public Enseignement[] chargeTousLesEnseignements() {
		String[] result = processCmd(operator + " -R -all -- modules.csv");
		Enseignement[] lesModules = new Enseignement[result.length - 1];
		int i = 0;
		for (String ligne : result) {
			if (i > 0) {
				String[] caracs = ligne.split(" ");
				lesModules[i - 1] = new Enseignement(caracs[0], Integer.parseInt(caracs[1]));
			}
			i++;
		}
		System.out.println(Arrays.toString(lesModules));
		return lesModules;

	}

	public Epreuve[] chargeToutesLesEpreuvesEtRelieLesModules(Enseignement[] lesModulesConnus) {
		String[] result = processCmd(operator + " -R -all -- epreuves.csv");
		Epreuve[] lesEpreuves = new Epreuve[result.length - 1];
		int i = 0;
		for (String ligne : result) {
			if (i > 0) {
				String[] caracs = ligne.split(" ");
				Enseignement moduleRelatif = chercheModuleParNom(lesModulesConnus, caracs[2]);
				lesEpreuves[i - 1] = new Epreuve(caracs[1], moduleRelatif);
			}
			i++;
		}
		System.out.println(Arrays.toString(lesEpreuves));
		return lesEpreuves;

	}

	public void chargeLesInscriptions(Etudiant[] lesEtudiants, Enseignement[] lesModulesConnus) {
		String[] result = processCmd(operator + " -R -all -- inscriptions.csv");
		int i = 0;
		for (String inscription : result) {
			if (i > 0) {
				String[] caracs = inscription.split(" ");
				Etudiant etudiantAInscrire = chercheEtudiantParIne(lesEtudiants, caracs[0]);
				Enseignement moduleAInscrire = chercheModuleParNom(lesModulesConnus, caracs[1]);
				etudiantAInscrire.addInscription(moduleAInscrire);
				moduleAInscrire.addInscrit(etudiantAInscrire);
			}
			i++;
		}
		System.out.println(Arrays.toString(lesEtudiants));
		System.out.println(Arrays.toString(lesModulesConnus));
	}

	public void chargeLesConvocations(Etudiant[] lesEtudiants, Epreuve[] lesEpreuves, Enseignement[] lesModulesConnus) {
		String[] result = processCmd(operator + " -R -all -- convocations.csv");
		int i = 0;
		for (String convocation : result) {
			if (i > 0) {
				String[] caracs = convocation.split(" ");
				Etudiant etudiantAConvoquer = chercheEtudiantParIne(lesEtudiants, caracs[0]);
				Epreuve epreuveAConvoquer = chercheEpreuveParNomEtModule(lesEpreuves, caracs[1], caracs[2], lesModulesConnus);
				etudiantAConvoquer.addEpreuve(epreuveAConvoquer);
				epreuveAConvoquer.addEtudiant(etudiantAConvoquer);
			}
			i++;
		}
		System.out.println(Arrays.toString(lesEtudiants));
		System.out.println(Arrays.toString(lesEpreuves));
	}
		
	public void enregistreLesEtudiants(Etudiant[] lesEtudiants) {
//		./persist -C 1 Migeon Frederic False -- etudiants.csv
		for(Etudiant etudiantAEnregistrer : lesEtudiants) {
			StringBuffer params = new StringBuffer();
			params.append(operator+" -C");
			params.append(" "+etudiantAEnregistrer.getIne());
			params.append(" "+etudiantAEnregistrer.getNom());
			params.append(" "+etudiantAEnregistrer.getPrenom());
			params.append(" "+etudiantAEnregistrer.beneficieTiersTemps());
			params.append(" -- etudiants.csv");
			System.out.println(params);
			processCmd(params.toString());
		}
	}
	
	public void enregistreLesModules(Enseignement[] lesModules) {
		for(Enseignement moduleAEnregistrer : lesModules) {
			StringBuffer params = new StringBuffer();
			params.append(operator+" -C");
			params.append(" "+moduleAEnregistrer.getTitre());
			params.append(" "+moduleAEnregistrer.getEcts());
			params.append(" -- modules.csv");
			System.out.println(params);
			processCmd(params.toString());
		}
		
	}

	public void enregistreLesEpreuves(Epreuve[] lesEpreuves) {
		// TODO Auto-generated method stub
		
	}

	private Epreuve chercheEpreuveParNomEtModule(Epreuve[] lesEpreuves, String nomEpreuve, String nomModule, Enseignement[] lesModulesConnus) {
		Enseignement module = chercheModuleParNom(lesModulesConnus,nomModule);
		
		int i;
		for (i = 0; i < lesEpreuves.length &&
				    !lesEpreuves[i].getNom().equals(nomEpreuve) &&
				    !lesEpreuves[i].getEnseignement().equals(module); i++)
			;
		return i < lesEpreuves.length ? lesEpreuves[i] : null;
	}

	private Etudiant chercheEtudiantParIne(Etudiant[] lesEtudiants, String ine) {
		int i;
		for (i = 0; i < lesEtudiants.length && !lesEtudiants[i].getIne().equals(ine); i++)
			;
		return i < lesEtudiants.length ? lesEtudiants[i] : null;
	}

	private Enseignement chercheModuleParNom(Enseignement[] lesModulesConnus, String nomModule) {
		int i;
		for (i = 0; i < lesModulesConnus.length && !lesModulesConnus[i].getTitre().equals(nomModule); i++)
			;
		return i < lesModulesConnus.length ? lesModulesConnus[i] : null;
	}

	private String[] processCmd(String cmd) {
		String[] params = cmd.split(" ");
		params[0] = path + File.separator + params[0];
		String[] lines = new String[0];
		Process process;
		try {
			process = new ProcessBuilder(params).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			lines = br.lines().toArray(String[]::new);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}


}
