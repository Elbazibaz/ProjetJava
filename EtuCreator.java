import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class EtuCreator {


    public EtuCreator(String path,int etunb) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();

        ArrayList<String> tronc = new ArrayList<String>();
        ArrayList<String> LV = new ArrayList<String>();
        ArrayList<String> options = new ArrayList<String>();
        tronc.addAll(Arrays.asList("Java","Genie logiciel","IA","Calcul distribue","Finance"));
        options.addAll(Arrays.asList("Architecture","C++","Compilation","Programme linéaire","Graphes","Communication","Theorie Decision","Marketing"));
        LV.addAll(Arrays.asList("Anglais","Allemand","Espagnol"));
        builder.append("id;");
        builder.append(String.join(";",tronc)+";");
        builder.append(String.join(";",options)+";"); // Un peu répétitif mais nécessaire si on le souhaite de prendre une liste de matières définie par l'utilisateur
        builder.append(String.join(";",LV)+";"); // ici ce n'est pas le cas car on reprend les matieres presentes dans le projet
        builder.append('\n');
        for (int i=0; i<etunb;i++){
            builder.append(String.valueOf(i)+";");
            for (int j=0;j<tronc.size();j++){
                builder.append(String.valueOf((int) Math.floor(Math.random()+0.8)) + ";"); // 80% de proba pour chaque matière du tronc de l'avoir
            }
            for (int j=0;j<options.size();j++){
                builder.append(String.valueOf((int) Math.floor(Math.random()+0.5)) + ";"); // 50% pour les options
            }
            for (int j=0;j<LV.size();j++){
                if (j==0){builder.append(String.valueOf( (int) Math.floor(Math.random()+0.9)) + ";");} // 90% pour l'anglais
                else{builder.append(String.valueOf((int) Math.floor(Math.random()+0.3)) + ";");}

            }
            builder.append('\n');


        }
        pw.write(builder.toString());
        pw.close();

    }


}
