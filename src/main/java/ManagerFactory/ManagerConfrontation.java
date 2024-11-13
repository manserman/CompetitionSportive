package ManagerFactory;

import BuilderConfrontation.ConfrontationBuilder;
import Component.Equipe;
import Component.Phase;
import Component.Poule;

import java.util.ArrayList;

public abstract class ManagerConfrontation {
    public enum ConfrontationType {
        EliminationDirecte, RoundRobin;
    }

    public static ManagerConfrontation getManager(ConfrontationType typeConfrontation){
        ManagerConfrontation manager = null;
        switch(typeConfrontation){
            case EliminationDirecte:
                manager = new ManagerConfrontationEliminationDirecte();
                break;
            case RoundRobin:
                manager = new ManagerConfrontationRoundRobin();
                break;
        }
        return manager;
    }

    protected Phase phase;

    protected ConfrontationType Type;

    protected ArrayList<Equipe> ResultatEquipes = new ArrayList<>();

    protected abstract ArrayList<Equipe> calculateResultPoule(Poule poule, int nbrEquipeSortante);

    public abstract void calculateResultPhase(Phase phase);

    protected void affichageConsoleResultatPhase(ArrayList<Equipe> equipesSortantes){
        System.out.println("---------------------------");
        System.out.println("Les equipes sortantes de la phase :");
        for(Equipe equipe : equipesSortantes){
            equipe.affichageConsole();
        }
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public ArrayList<Equipe> getResultatEquipe() {
        return ResultatEquipes;
    }
}
