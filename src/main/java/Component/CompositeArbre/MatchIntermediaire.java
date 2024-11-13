package Component.CompositeArbre;

import Component.Resultat;
import ManagerFactory.ManagerConfrontation;

public class MatchIntermediaire extends ElementConfrontation {
    private ElementConfrontation MatchA;
    private ElementConfrontation MatchB;

    public MatchIntermediaire(String date, Resultat resultat, ElementConfrontation matchA, ElementConfrontation matchB) {
        super(date, resultat);
        this.MatchA = matchA;
        this.MatchB = matchB;
    }

    @Override
    public  void affichageConsoleResultat() {
        MatchA.affichageConsoleResultat();
        MatchB.affichageConsoleResultat();
        System.out.println(MatchA.getResultat().getEquipeVictoire().getNom() + " vs " + MatchB.getResultat().getEquipeVictoire().getNom() + " : " + getResultat().affichage());
    }

    @Override
    public void setConsoleResultat(ManagerConfrontation.ConfrontationType type) {
        //On lance le résultat des deux matchs d'avant
        this.MatchA.setConsoleResultat(type);
        this.MatchB.setConsoleResultat(type);

        //Demander des informations sur le Match
        System.out.println("--- Informations sur le match " + this.MatchA.getResultat().getEquipeVictoire().getNom()  + " vs " +  this.MatchB.getResultat().getEquipeVictoire().getNom() + " : ---");

        //Demande sur la date du match
        this.askForDate();

        //Demande sur le resultat
        this.askForResultat(this.MatchA.getResultat().getEquipeVictoire(),this.MatchB.getResultat().getEquipeVictoire(), type);
        System.out.println("---------------------------");
    }

    @Override
    public Resultat accesResultat() {
        return getResultat();
    }
}