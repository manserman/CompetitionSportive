package Component;

import Component.CompositeArbre.ElementConfrontation;

import java.util.ArrayList;

public class Poule {
    private String Nom;
    private ArrayList<ElementConfrontation> ListeDesMatchs;
    private ArrayList<Equipe> ListesEquipes;// tableau de pointeurs vers les équipes qui sont dans la poule.
    public Poule(String nom, ArrayList<ElementConfrontation> listeDesMatchs, ArrayList<Equipe> ListesEquipes){
        this.Nom = nom;
        this.ListeDesMatchs = listeDesMatchs;
        this.ListesEquipes=ListesEquipes;
    }

    public String getNom() {
        return Nom;
    }

    public ArrayList<ElementConfrontation> getMatchs(){
        return ListeDesMatchs;
    }

    public ArrayList<Equipe> getListesEquipes() {
        return ListesEquipes;
    }
}
