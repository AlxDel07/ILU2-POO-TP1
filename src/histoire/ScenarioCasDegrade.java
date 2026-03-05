package histoire;
import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) { 
		Etal etal = new Etal(); 
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		//etal.occuperEtal(bonemine, "fleurs", 20);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		try {
		etal.acheterProduit(5,assurancetourix);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Test acheterProduit non passé");
		}catch(IllegalStateException e) {
			e.printStackTrace();
			System.out.println("Un étal est vide");
		}
		System.out.println("Fin du test"); 
		} 
}
