package Component.CompositeArbre;

import Component.Equipe;
import Component.Resultat;
import ManagerFactory.ManagerConfrontation;

import java.util.Scanner;

public class MatchInitial extends ElementConfrontation {
    private Equipe EquipeA;
    private Equipe EquipeB;

    public MatchInitial(String date, Resultat resultat, Equipe equipeA, Equipe equipeB) {
        super(date, resultat);
        this.setEquipeA(equipeA);
        this.setEquipeB(equipeB);
    }

    @Override
    public void affichageConsoleResultat() {
        System.out.println(EquipeA.getNom() + " vs " + EquipeB.getNom() + " : " + getResultat().affichage()); ;
    }

    @Override
    public void setConsoleResultat(ManagerConfrontation.ConfrontationType type) {
        //Demander des informations sur le Match
        System.out.println("--- Informations sur le match " + this.getEquipeA().getNom()  + " vs " +  this.getEquipeB().getNom() + " : ---");

        //Demande sur la date du match
        this.askForDate();

        //Demande sur le resultat
        this.askForResultat( this.getEquipeA(), this.getEquipeB(), type);
        System.out.println("---------------------------");
    }

    @Override
    public Resultat accesResultat() {
        return getResultat();
    }

    public Equipe getEquipeA() {
        return EquipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        EquipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return EquipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        EquipeB = equipeB;
    }
}