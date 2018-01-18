/**
 * 
 * Cette classe permet de représenter un cours, que ce soit un Amphi ou un TD. 
 * NumTD représente le numéro du TD, si il s'agit d'un TD. La variable name est le nom du cours ou TD.
 * Amphi est true si le cours est en fait un Amphi et est donc false si il s'agit d'un TD.
 *
 */
public class Cours {
	private int NumTD;
	private String name;
	private boolean Amphi;
	
	
	public Cours(String nameCours,int num) {
		this.name=nameCours;
		this.NumTD=num;
		
	}
	public Cours(String nameCours,boolean amph) {
		this.name=nameCours;
		this.Amphi=true;
		
	}
	
	public int get_NumTD() {
		return this.NumTD;
	}
	public String get_Name() {
		return this.name;
	}
	public boolean Amphi() {
		return this.Amphi;
	}
	public void Afficher() {
		System.out.println(name+NumTD+"  amphi: "+Amphi);
	}
}
