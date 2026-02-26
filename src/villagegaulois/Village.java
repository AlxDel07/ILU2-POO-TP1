package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];	
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalsProduit = 0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].contientProduit(produit)) {
					nbEtalsProduit++;
				}
			}
			Etal[] etalsProduit = new Etal[nbEtalsProduit];
			int compteur = 0;
			for(int j=0;j<etals.length;j++) {
				if(etals[j].contientProduit(produit)) {
					etalsProduit[compteur] = etals[j];
					compteur++;
				}
			}
			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois vendeur) {
			for(int i=0;i<etals.length;i++) {
				Gaulois vendeurEtal = etals[i].getVendeur();
				if(vendeurEtal.equals(vendeur)) {
					return etals[i];
				}
			}
			return null;
		}
		
		private void afficherMarche() {
			int nbEtalsVides = 0;
			for(Etal etalaffiche:etals) {
				if(etalaffiche.isEtalOccupe()) {
					etalaffiche.afficherEtal();
				}else {
					nbEtalsVides++;
				}
			}
			if(nbEtalsVides > 0) {
				System.out.println("Il reste " + nbEtalsVides + " étals non utilisés dans le marché");
			}
		}
	}

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}