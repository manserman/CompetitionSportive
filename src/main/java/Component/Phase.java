package Component;

import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;

public class Phase {
    private int Numero;
    private String Nom;
    private ArrayList<Poule> Poules;

    private ManagerConfrontation managerConfrontation;

    public Phase(int numero, String nom, ArrayList<Poule> poules){
        this.Nom = nom;
        this.Numero = numero;
        this.Poules = poules;
    }

    public int getNumero() {
        return Numero;
    }

    public String getNom() {
        return Nom;
    }

    public ArrayList<Poule> getPoules(){
        return Poules;
    }

    public ManagerConfrontation getManagerConfrontation() {
        return managerConfrontation;
    }

    public void setManagerConfrontation(ManagerConfrontation managerConfrontation) {
        this.managerConfrontation = managerConfrontation;
    }
}
