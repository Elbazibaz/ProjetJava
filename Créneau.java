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
		for(Cours c: list) {
			if(c instanceof TD) {
				((TD) c).afficher();
			}
			else c.Afficher();
		}
		System.out.println("########################\n");
	}
	public int get_Nbr_td(String namee) {
		int nbr_td=0;
		for(Cours c:this.list) {
			if(c.get_Name().contains(namee) && c instanceof TD) nbr_td++;
		}
		return nbr_td;
	}

	/**
	 * Cette méthode permet de gérer le conflit dans l'emploi du temps. En effet elle permet de savoir si 
	 * deux TD du même groupe de TD sont dans le même créneau ou si un amphi et un TD de matières qu'a choisi
	 * un étudiant sont dans le même créneau. 
	 * On considère la présence en Amphi obligatoire.
	 * 
	 * @param MatEtudiant La liste de matière qu'a choisi l'étudiant.
	 * @param td Le numéro du groupe de TD affecté à l'étudiant.(pour le test)
	 * @return Renvoie un boolean. TRUE : Il n'y a pas de conflit; FALSE : Il y a conflit.
	 *
	 */
	public boolean recherche_conflit(ArrayList<String> MatEtudiant, int td) {
		boolean res=true;
		int compteur=0;
		for(Cours c: list) {
			if (c instanceof Amphi && MatEtudiant.contains(c.get_Name().toLowerCase())) {
				compteur++;
			}
				
			if(c instanceof TD && MatEtudiant.contains(c.get_Name().toLowerCase()))
				if(((TD) c).get_NumTD()==td)compteur++;
		}
		if(compteur >1)res=false;
		return res;
	}
}
