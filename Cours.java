/**
 * 
 * Cette classe permet de représenter un cours, que ce soit un Amphi ou un TD. 
 * 
 *
 */
public class Cours {
	private String name;
	
	
	public Cours(String nameCours) {
		this.name=nameCours;
		
	}
	public String get_Name() {
		return this.name;
	}

	public void Afficher() {
		System.out.println(name);
	}
}
