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
        String line;
        String sep;
        String edt;
        String etu;

        public ReaderCSV(String s1,String s2) {
            line=null;
            sep=";";
            edt=s2;
            etu=s1;
        }
        public void read() {
            String[] tab ;
            Map <Integer, ArrayList<String>> etulist = new HashMap<Integer, ArrayList<String> >();
            try {
                BufferedReader in= new BufferedReader(new FileReader(this.etu));

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
