import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws ConflitCreneauException, LimiteNbrEtuException {
		
		/*int p=1;
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
		Scanner scan= new Scanner(System.in);
		p=scan.nextInt();
		while(p!=0) {
			p=scan.nextInt();
			ReaderCSV reader = new ReaderCSV();
			switch (p) {
			case 1 :
				reader.read();
				break;
			case 2 :
				Scanner scanfich=new Scanner(System.in);
				System.out.println("Entrez le chemin du fichier Etudiant :");
				String etu=scanfich.next();
				System.out.println("Entrez le chemin du fichier Emploi du temps :");
				String edt=scanfich.next();
				if(edt.)
				ReaderCSV reader = new ReaderCSV();
				break;
			case 3 :
				Gestionnaire gestionnaire=new Gestionnaire(reader.get_etudiants(),reader.get_EDT());
				gestionnaire.Assignation
				break;
			case 4 :
			
				Scanner scanlimite=new Scanner(System.in);
				gestionnaire.set_LimiteEtu(scan.nextInt()),reader.get_listeCours());
				break;

			}
			
		}
		*/
		try {
		ReaderCSV reader = new ReaderCSV();
		reader.read();
		Gestionnaire gestionnaire=new Gestionnaire(reader.get_etudiants(),reader.get_EDT());
		gestionnaire.sortie_TDS(gestionnaire.Assignation(),reader.get_listeCours());
		//for(Créneau c:edt)c.Afficher();
		}
		catch(ConflitCreneauException e) {
			e.printStackTrace();
			System.out.println("creneau");
		}
		catch(LimiteNbrEtuException e) {
			e.printStackTrace();
			System.out.println("limite");
		}
		
	}

}
