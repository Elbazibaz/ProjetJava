import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Etudiant implements Serializable {
    private int id;
    private int td;
    private ArrayList<String> matieres;

    public Etudiant(int id,ArrayList<String> matieres){
        id=id;
        matieres=matieres;
    }

    public void setId(int numero){
        this.id=numero;
    }


}
