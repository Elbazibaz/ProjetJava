/**
 * Cette classe permet d'avoir des informations concernant le nombre d'étudiant assigné à un TD.
 * 
 *
 */
public class TD extends Cours{
	private int NumTD;
	private int NbrEtu;
	
	public TD(String nameCours,int numtd) {
		super(nameCours);
		this.NumTD=numtd;
		this.NbrEtu=0;
	}	
	
	public int get_NumTD() {
		return this.NumTD;
	}
	public int get_NbrEtu() {
		return this.NbrEtu;
	}
	public void afficher() {
		super.Afficher();
		System.out.println(this.NumTD);
	}
	public void incremente_NbrEtu() {
		this.NbrEtu++;
	}
}
