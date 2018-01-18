import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		int p=1;
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
			
			switch (p) {
			case 1 :
				ReaderCSV reader = new ReaderCSV();
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
				
				break;
			case 4 :
				
				break;
			default :
				System.out.println("Veuillez")
			}
			
		}
		
		ReaderCSV reader = new ReaderCSV();
		reader.read();
		ArrayList<Créneau> edt=reader.readEDT();
		for(Créneau c:edt)c.Afficher();
		
	}

}
