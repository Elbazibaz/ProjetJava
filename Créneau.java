import java.util.ArrayList;
/**
 * 
 * Cette classe permet de représenter un créneau horaire de l'emploi du temps d'entrée. Il s'agit donc intuitivement
 * d'une liste de cours. @see Cours
 *
 */
public class Créneau {
	private ArrayList<Cours> list;
	
	public Créneau() {
		this.list=new ArrayList<>();
	}
	
	public ArrayList<Cours> get_creneau(){
		return this.list;
	}

	
	public void Afficher() {
		for(Cours c: list)c.Afficher();
		System.out.println("########################\n");
	}
}
