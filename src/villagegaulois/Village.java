package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for(int i=0;i<nbEtals;i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i=0;i<etals.length;i++) {
				if(!(etals[i].isEtalOccupe())) {
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
				if(etals[i].isEtalOccupe()) {
					Gaulois vendeurEtal = etals[i].getVendeur();
					if(vendeurEtal.equals(vendeur)) {
						return etals[i];
					}
				}	
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsVides = 0;
			for(Etal etalaffiche:etals) {
				if(etalaffiche.isEtalOccupe()) {
					chaine.append(etalaffiche.afficherEtal());
				}else {
					nbEtalsVides++;
				}
			}
			if(nbEtalsVides > 0) {
				chaine.append("Il reste " + nbEtalsVides + " étals non utilisés dans le marché \n");
			}
			return chaine.toString();
		}
	}

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
		
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
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		int numEtal = marche.trouverEtalLibre() + 1;
		marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n° " + numEtal);
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		int longueur = etalsProduit.length;
		if(longueur == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché \n");
		}else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont : \n");
			for(int i=0;i<longueur;i++) {
				Etal etal = marche.etals[i];
				if(etal.contientProduit(produit)) {
					Gaulois vendeur = etal.getVendeur();
					chaine.append("- " + vendeur.getNom() + "\n");
				}
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etalVendeur = this.rechercherEtal(vendeur);
		if(etalVendeur == null) {
			chaine.append("Ce gaulois n'a pas d'étal actuellement \n");
		}else {
			chaine.append(etalVendeur.libererEtal());
		}
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village " + this.nom + " possède plusieurs étals :\n");
		for(int i=0;i<marche.etals.length;i++) {
			marche.etals[i].afficherEtal();
		}
		return chaine.toString();
	}
	
}