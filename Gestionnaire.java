import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe permet d'effectuer les opérations algorithmiques du programme. C'est ici qu'à lieu l'assignation
 * des étudiants dans les TDS.
 * 
 *
 */
public class Gestionnaire {
	private ArrayList<Créneau> listedt;
	private Map<Integer,ArrayList<String>> map;
	private int LimiteEtu;
	
	public Gestionnaire(Map<Integer,ArrayList<String>> map1,ArrayList<Créneau> list) {
		this.map=map1;
		this.listedt=list;
		this.LimiteEtu=35;
	}
	public Map<Integer,ArrayList<String>> get_map(){
		return this.map;
	}
	public ArrayList<Créneau> get_edt(){
		return this.listedt;
	}
	public int get_EtuTD() {
		return this.LimiteEtu;
	}
	public void set_EtuTD(int nbr) {
		this.LimiteEtu=nbr;
	}
	/**
	 * L'utilité de cette classe interne est de pouvoir classer les matières par rapport à leur nombre de TD.
	 *
	 *
	 */
	public class Matiere {
		private int NbrTD;
		private String name;
		private ArrayList<Integer> listNumTD;
		public Matiere(String name) {
			listNumTD=new ArrayList<Integer>();
			int compteur=0;
			this.name=name;
			for(Créneau c:listedt) compteur=compteur+c.get_Nbr_td(name);
			for(Créneau c:listedt) {
				for(Cours cours:c.get_creneau()) {
					if(cours instanceof TD && cours.get_Name().contains(name))listNumTD.add(((TD)cours).get_NumTD());
				}
			}
			this.NbrTD=compteur;
		}
		public int get_NbrTD() {
			return this.NbrTD;
		}
		public String get_name() {
			return this.name;
		}
		public ArrayList<Integer> get_listnumTD(){
			return this.listNumTD;
		}
		public void afficher() {
			System.out.println(this.name+NbrTD+listNumTD.toString());
		}
	}
	/**
	 * On cherche à trier une liste de matières par rapport au nombre total de TD.
	 * @param listmat C'est une liste de matieres
	 * @return retourne une liste de matière triés
	 */
	public void sort_matiere(ArrayList<Matiere> listmat) {
		class OrdreMatiere implements Comparator<Matiere>{

			@Override
			public int compare(Matiere mat1, Matiere mat2) {
				
				return mat1.get_NbrTD()-mat2.get_NbrTD();
			}
			
		}
		OrdreMatiere ordre=new OrdreMatiere();
		Collections.sort(listmat,ordre);
	}
	/**
	 * C'est la méthode de sortie. Elle écrit dans un fichier CSV les informations concernant l'assisgnation dans les groupes de TD.
	 * La première ligne est constitué des noms d'amphi et TDS.
	 * Les lignes suivantes correspondent à l'étudiant puis 0 ou 1 selon l'assignation dans un TD (sachant que l'amphi est obligatoire).
	 * @param map C'est la deuxième map du programme. C'est celle qui est retournée par la méthode Assignation. Elle a en clé l'identifiant de l'étudiant
	 * et en valeur une liste de String avec les noms des TD suivis et Amphi des matières.
	 * @param listeCours C'est la liste de cours globale : c'est à dire Amphi et TD.
	 */
	public void sortie_TDS(Map<Integer,ArrayList<String>> map,ArrayList<String> listeCours) {
		try (BufferedWriter out= new BufferedWriter(new FileWriter(new File("Sortie.csv")))){
			out.write("ID");
			out.flush();
			for(String s : listeCours) {
				out.write(";"+s);
				out.flush();
			}
			out.write("\n");
			out.flush();
			System.out.println("1232123123");
			
			for(Map.Entry<Integer, ArrayList<String>> paires: map.entrySet()) {
				out.write(paires.getKey().toString());
				out.flush();
				System.out.println(paires.getValue().toString());
				for(String s:listeCours) {
					if(paires.getValue().contains(s))out.write(";1");
					
					else out.write(";0");
					out.flush();
				}
				out.write("\n");
				out.flush();
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	/** 
	 * Cette classe avait pour but d'éviter d'avoir à un instancier une arraylist de matière dans la boucle de la méthode Assignation. Ceci pour pallier
	 * un choix probablement pas optimale concernant le choix d'une classe.
	 * 
	 *
	 */
	public class Listmat{
		private ArrayList<Matiere> listmatetu;
		public Listmat(ArrayList<String> list) {
			listmatetu=new ArrayList<Matiere>();
			for(String s : list )listmatetu.add(new Matiere(s));
		}
		public ArrayList<Matiere> get_listmat(){
			return this.listmatetu;
		}
	}
	/**Cette méthode établit l'assignation des étudiants dans leur groupe de TD si c'est possible.
	 * 
	 * @return Renvoie une map avec en clé l'ID de l'étudiant et en valeur la liste des cours suivis (Amphi+TD)
	 * @throws ConflitCreneauException Si il ya conflit entre 2 matières choisies ( ou un amphi et son TD)
	 * @throws LimiteNbrEtuException Si il n'y a plus de place dans les TDs possible d'un étudiant.
	 */
	public Map<Integer,ArrayList<String>> Assignation() throws ConflitCreneauException,LimiteNbrEtuException{

		Map<Integer,ArrayList<String>> mapSortie=new HashMap<Integer,ArrayList<String>>();
		int groupeTD;
		ArrayList<String> liststring;
		ArrayList<TD> listTD=new ArrayList<>();
		Listmat listmatetu;
		/*ArrayList<Matiere> listmatetu=new ArrayList<Matiere>();
		for(Créneau courant:listedt) {
			for(Cours cours:courant.get_creneau()) {
				if(cours instanceof TD)listmatetu.add(new Matiere(cours.get_Name()));
			}
		}*/
		boolean PasDeConflit=true;
		boolean LimTDCompatible=true;
		for(Créneau courant:listedt) {
			for(Cours c:courant.get_creneau()) if(c instanceof TD)listTD.add((TD) c);
		}
		try{
			for(Map.Entry<Integer, ArrayList<String>> paires: get_map().entrySet()) {

				liststring=paires.getValue();
				listmatetu=new Listmat(liststring);
				//System.out.print(listmatetu.size());
				for(Matiere mat:listmatetu.get_listmat())Collections.sort(mat.listNumTD);

				sort_matiere(listmatetu.get_listmat());
				//for(Matiere mat:listmatetu)mat.afficher();
					
				
				for(int j=0;j<=listmatetu.get_listmat().get(0).listNumTD.size()-1;j++) {
					PasDeConflit=true;
					LimTDCompatible=true;
					groupeTD=listmatetu.get_listmat().get(0).listNumTD.get(j);
					
					// Recherche de conflit dans l'emploi du temps :
					for(Créneau courant:listedt) {
						if(!courant.recherche_conflit(liststring, groupeTD)) {
							PasDeConflit=false;
							break;
						}
					}
					
					// Vérification limite du nombre étudiant par TD :
					if(PasDeConflit) {
						for(TD td:listTD) {
							if(liststring.contains(td.get_Name()) && td.get_NumTD()==groupeTD) {
								if(td.get_NbrEtu()>=LimiteEtu) {
									LimTDCompatible=false;
									break;
								}
								else td.incremente_NbrEtu();
							}
						}
					}
					// Si il n'y a pas de conflit dans l'EDT ni dans le nombre d'étudiant dans le TD; alors on assigne le TD à l'étudiant.
					
					if(PasDeConflit && LimTDCompatible) {
						ArrayList<String> listfinal=new ArrayList<String>();
						for(Créneau c:listedt) {
							for(Cours cours:c.get_creneau()) {
								if(cours instanceof TD && liststring.contains(cours.get_Name()))listfinal.add(cours.get_Name().concat("_"+groupeTD));
								if(cours instanceof Amphi && liststring.contains(cours.get_Name()))listfinal.add(cours.get_Name());
							}
						}
						mapSortie.put(paires.getKey(),listfinal);
						System.out.println(listfinal.toString()+"FINAL STRING");
					}
					if(j==listmatetu.get_listmat().get(0).get_listnumTD().size()-1 && PasDeConflit==false)throw new ConflitCreneauException();
					if(j==listmatetu.get_listmat().get(0).get_listnumTD().size()-1 && LimTDCompatible==false)throw new LimiteNbrEtuException();
					//if(j==listmatetu.get(0).get_NbrTD()-1 && PasDeConflit==false)throw new ConflitCreneauException();
					//if(j==listmatetu.get(0).get_NbrTD()-1 && LimTDCompatible==false)throw new LimiteNbrEtuException();
					
				}	
				

			}	
		}
		catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("");
		}
		catch(ConflitCreneauException e) {
			e.printStackTrace();
			System.out.println("Il y a un conflit dans les créneaux de l'emploi du temps.");
		}
		catch(LimiteNbrEtuException e) {
			e.printStackTrace();
			System.out.println("La limite du nombre d'étudiants par TD est trop petite pour qu'il y est une solution.");
			
		}
		return mapSortie;
	}
}
