import java.util.ArrayList;

public class main {

	public static void main(String[] args) {

		int p = 1;
		System.out.println("Voici les options disponibles :");
		System.out.println("################");
		System.out.println("0-Quitter.");
		System.out.println("################");
		System.out.println("1-Lecture des fichiers par défaut.");
		System.out.println("################");
		System.out.println("2-Lecture de fichier spécifique.");
		System.out.println("################");
		System.out.println("3-Répartir les étudiants dans les TD.");
		System.out.println("################");
		System.out.println("4-Changer la capacité des TD.");
		System.out.println("################");
		System.out.println("################");


		ReaderCSV reader = new ReaderCSV("lol", "F:\\Users\\Vincent\\Desktop\\ProjetJava\\etu.csv");
		reader.readEtu();
		ArrayList<ArrayList<String>> liste_brute = new ArrayList<ArrayList<String>>();
		liste_brute = reader.liste_etudiants;
		/*TEST TD td_0= new TD(0);
		for (ArrayList<String> value : liste_brute) {
			Etudiant etudiant = new Etudiant(liste_brute.indexOf(value),value);
			td_0.liste.add(etudiant);
			System.out.println(value);}
		}
		*/



		/*while(p!=0) {
			
			
			switch (p) {
			case 1 :

				break;
			case 2 :
				
				break;
			case 3 :
				
				break;
			case 4 :
				
				break;
				
			}
			
		}*/


	}}