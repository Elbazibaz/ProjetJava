import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Cette classe permet d'effectuer les opérations algorithmiques du programme. C'est ici qu'à lieu l'assignation
 * des étudiants dans les TDS.
 * 
 *
 */
public class Gestionnaire {
	
	
	public Gestionnaire() {
		// il doit y avoir une map d'étudiants et une liste de créneau 
	}
	public void sort_creneau() {
		class OrdreCreneau implements Comparator<Créneau>{

			@Override
			public int compare(Créneau arg0, Créneau arg1) {
				
				return 0;
			}
			
		}
	}
	
	public void sortie_TDS(Map<Integer,ArrayList<String>> map) {
		File file = new File("Sortie.csv");
		BufferedWriter out;
		try {
			out= new BufferedWriter(new FileWriter(file));
			for(Map.Entry<Integer, ArrayList<String>> paires: map.entrySet()) {
				out.write(format(paires.getKey().toString()));
				out.
			}
				
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		finally {
			out.close();
		}
		
	}
	
	public void Assignation(Map<Integer,ArrayList<String>> map, ArrayList<Créneau> list) {
		for(Map.Entry<Integer, ArrayList<String>> paires: map.entrySet()) {
			// La on égalise la clé de la map avec cette map
			int etu=paires.getKey();
			// maintenant dans ma nouvelle map on doit ajouter les cours qu'il suit ?
			// C'est à dire Statistiques_3 Anglais_3
			matieres=
					
		}
		
	}
}
