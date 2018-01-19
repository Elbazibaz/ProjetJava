import java.io.BufferedInputStream;
import java.io.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
/**
 * Cette classe permet de d'effectuer toutes les opérations d'extraction. On dispose de deux constructeurs 
 * différents selon les options; on peut soit utiliser le mode par défaut où les fichiers edt.csv et etu.csv
 * sont considérés être dans le même répertoire que mes fichiers sources, soit le mode personalisé ou l'on peut
 * détaillé les chemins des fichiers.
 * 
 *
 */
public class ReaderCSV {
       private String line;
       private String sep;
       private String edt;
       private String etu;
       private ArrayList<String> listeCours;
       private Map<Integer,ArrayList<String>> etudiants;
       private ArrayList<Créneau> EDT;
        public ReaderCSV() {
        		line=null;
        		sep=";";
        		edt="edt.csv";
        		etu="etu.csv";
        		listeCours=new ArrayList<>();
        		etudiants=this.read();
        		EDT=this.readEDT();
        }
        public ReaderCSV(String s1,String s2) {
            line=null;
            sep=";";
            edt=s2;
            etu=s1;
            etudiants=this.read();
            EDT=this.readEDT();
        }
        /**
         * Cette méthode permet l'extraction du fichier étudiants et regroupe toutes les informations dans une 
         * Map.
         * @return
         * Cette méthode nous retourne une map constitué des étudiants en clé (uniques) et des matières qu'ils ont choisies en valeur.
         * 
         * @throws FileNotFoundException
         * @throws IOException
         * @throws NumberFormatException 
         */
        public Map<Integer,ArrayList<String>> read() {
            String[] tab ;
            Map <Integer, ArrayList<String>> etulist = new HashMap<>();
            try(BufferedReader in= new BufferedReader(new FileReader(this.etu))) {

                line=in.readLine();
                String [] matieres=line.split(";",-1);
                for(int j=0;j<matieres.length;j++)matieres[j].toLowerCase();
                line=in.readLine();
                while(line!=null) {
                    ArrayList<String> etumatieres = new ArrayList<String>();
                    tab=line.split(";");
                    etulist.put(Integer.parseInt(tab[0]),etumatieres);
                    for (int i=1;i<tab.length;i++){
                        if (tab[i].equals("1")){
                            etumatieres.add(matieres[i]);
                            etulist.put(Integer.parseInt(tab[0]),etumatieres);
                        }
                    }
                    line=in.readLine();
                }
               // for(Map.Entry<Integer,ArrayList<String>> paires : etulist.entrySet()) {
                	//	ArrayList<String> parcours=paires.getValue();
                //		System.out.println(parcours.toString());
                //}
                in.close();
            }
            catch(NumberFormatException e) {
            		e.printStackTrace();
            		System.out.println("Un numéro de TD semble avoir été mal entré dans : "+etu);
            }
            catch(FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Le fichier etu n'existe pas !");
            }
            catch(IOException e) {
                e.printStackTrace();
            }
			return etulist;

        }
        /**
         * 
         * 
         * 
         * Cette méthode permet d'extraire les informations de l'emploi du temps c'est à dire la réprésentation 
         * de l'emploi du temps mis en entrée. On utlisera des objets du type Créneau pour représenter un créneau 
         * de l'emploi du temps. On a ignoré les créneaux vides.
         * 
         * @return
         * Retourne une Arraylist de créneaux.
         * @throws FileNotFoundException
         * @throws ArrayIndexOutOfBoundsException
         * @throws IOException
         * 
         */
       public ArrayList<Créneau> readEDT(){
    	   
    	       String tab[];
    	       String tab2[];
    	       String tab3[];
    	       ArrayList<Créneau> listedt=new ArrayList<>();
    	       try(BufferedReader in= new BufferedReader(new FileReader(this.edt))) {
	           line=in.readLine();
	           line=in.readLine();
	           while(line!=null) {
	        	   		tab2=null;
	        	   		tab3=null;
	        	   		tab=line.split(";",-1);
	        	   		for(int k=0;tab.length<k;k++)tab[k].toLowerCase();
	        	   		for(int i=0;i<tab.length;i++) {
	        	   			//System.out.println(tab[i]);
	        	   			tab2=tab[i].split(" ",-1);
	        	   			//System.out.println(tab.length);
	        	   			// tableau de cours d'un créneau
	        	   			// 
	        	   			if(tab2.length>0 && !tab[i].isEmpty())listedt.add(new Créneau()); // créer des créneaux en trop
	        	   			for(int j=0;j<tab2.length;j++) {
	        	   				tab3=null;
	        	   				tab3=tab2[j].split("_",-1); // tableau cours + underscore normalement 2 éléments
	        	   				
	        	   				if(tab2[j].contains("_"))listedt.get(listedt.size()-1).get_creneau().add(new TD(tab3[0],Integer.parseInt(tab3[1])));
	        	   				else if(tab3[0]!=null &&!tab3[0].isEmpty())listedt.get(listedt.size()-1).get_creneau().add(new Amphi(tab3[0]));
	        	   			}
	        	   		}
	        	   		line=in.readLine();
	           }
	           
    	       // Création de la liste de cours pour le fichier sortie
            for(Créneau c:listedt) {
    	    			for(Cours cours:c.get_creneau()) {
	    	    			if(cours instanceof Amphi)listeCours.add(cours.get_Name());
	    	    			if(cours instanceof TD) {
	    	    				listeCours.add(cours.get_Name().concat("_"+((TD) cours).get_NumTD()));
	    	    			}
    	    			}
    	       	}
    	       }
    	       
    	       
    	       
    	    catch(ArrayIndexOutOfBoundsException e) {
    	    	   e.printStackTrace();
    	    	   System.out.println("Le format du fichier emploi du temps n'est pas valide");
    	    }
    	   	catch(FileNotFoundException e){
    	   		e.printStackTrace();
    	   		System.out.println("Le fichier EDT n'existe pas !");
    	   	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }	
    	       return listedt ; 
       }
       
       public Map<Integer,ArrayList<String>> get_etudiants(){
    	   		return this.etudiants;
       }
       public ArrayList<Créneau> get_EDT(){
    	   		return this.EDT;
       }
       public ArrayList<String> get_listeCours(){
    	   		return this.listeCours;
       }

}
