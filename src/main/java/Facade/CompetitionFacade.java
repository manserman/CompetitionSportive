package Facade;

import BuilderConfrontation.ConfrontationBuilder;
import Component.Competition;
import Component.Equipe;
import Component.Phase;
import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;
import java.util.Scanner;

public class CompetitionFacade {

    private ConfrontationBuilder confrontationBuilder;

    private ArrayList<Competition> competitions = new ArrayList<>();

    //Permet la création d'une competition
    public Competition createCompetition(String nomCompetition, String categoryCompetition){
        return new Competition(nomCompetition,categoryCompetition);
    }

    //Permet d'ajouter une competition à la liste des competitions
    public void addCompetiton(Competition compet){
        this.competitions.add(compet);
    }

    //Permet de créer une phase avec son mode de confrontation avec le builder, on peut donner soir une liste d'equipe créée ou sinon null
    public Phase createManagerConfrontationPhase(Competition competition, ConfrontationBuilder cb, ArrayList<ArrayList<Equipe>> equipes){
        setConfrontationBuilder(cb);
        this.confrontationBuilder.createNewConfrontation();
        this.confrontationBuilder.buildPhase(competition, equipes);
        return this.confrontationBuilder.getManagerConfrontation().getPhase();
    }

    //Permet de mettre en place le choix du builder
    private void setConfrontationBuilder(ConfrontationBuilder cb){
        this.confrontationBuilder = cb;
    }

    //Met en place les resultats d'une phase
    public ArrayList<Equipe> resultatPhase(Phase phase){
        phase.getManagerConfrontation().calculateResultPhase(phase);
        return phase.getManagerConfrontation().getResultatEquipe();
    }

    //Recupere le resultat d'une phase
    public ArrayList<Equipe> getResultatPhase(Phase phase){
        return phase.getManagerConfrontation().getResultatEquipe();
    }
}
