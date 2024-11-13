package Component;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Competition {
    private String Nom;
    private String Categorie;
    private ArrayList<Phase> Phases;

    public Competition(String nom, String categorie){
        this.setNom(nom);
        this.setCategorie(categorie);
        this.Phases = new ArrayList<>();
    }

    public String getNom() {
        return Nom;
    }

    public String getCategorie() {
        return Categorie;
    }

    public ArrayList<Phase> getPhases(){
        return Phases;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public void setPhases(ArrayList<Phase> phases) {
        Phases = phases;
    }

    public void addPhase(Phase phase) {
        this.Phases.add(phase);
    }
}
