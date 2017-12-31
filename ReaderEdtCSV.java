import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

public class ReaderEdtCSV {
	
	String line;
	String sep;
	String edt;
	String etu;
	
	public ReaderEdtCSV() {
		line=null;
		sep=";";
		edt="edt.csv";
		etu="etus.csv";
	}
	
	public ReaderEdtCSV(String s1,String s2) {
		line=null;
		sep=";";
		edt=s1;
		etu=s2;
	}
	public void read() {
		String[] tab;
		int compteur=0;
		try {
			BufferedReader in= new BufferedReader(new FileReader(this.edt));
			
			line=in.readLine();
			while(line!=null) {
				tab=line.split(";");
				if (compteur==0);
				
				
				line=in.readLine();			}
			//appel objet constructeur etudiant
			// On peut retourner une liste d'etudiants et une structure modlisant l
			//l'emploi du temps
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Le fichier n'existe pas !");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
