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

public class ReaderCSV {
        private String line;
        private String sep;
        private String edtfile;
        private String etufile;
        public Map <Integer, ArrayList<String>> etulist;
        public ArrayList<ArrayList<String>> liste_etudiants; // correspond a une liste de listes de matières

        public ReaderCSV(String s1,String s2) {
            line=null;
            sep=";";
            edtfile=s1;
            etufile=s2;
            etulist = new HashMap<Integer, ArrayList<String> >();
        }
        public void readEtu() {
            String[] tab ;
            try {
                BufferedReader in= new BufferedReader(new FileReader(etufile));

                line=in.readLine();
                String [] matieres=line.split(";",-1);
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
                liste_etudiants = new ArrayList<ArrayList<String>>();
                for (ArrayList<String> value : etulist.values()) {
                    liste_etudiants.add(value);}

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

