package ManagerFactory;

import Component.CompositeArbre.ElementConfrontation;
import Component.Equipe;
import Component.Phase;
import Component.Poule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ManagerConfrontationEliminationDirecte extends ManagerConfrontation {

    public ManagerConfrontationEliminationDirecte(){
        this.Type = ConfrontationType.EliminationDirecte;
    }

    //Calcule le resultat d'une poule de phase elimination directe
    @Override
    public ArrayList<Equipe>  calculateResultPoule(Poule poule, int nbrEquipeSortante) {
        //Pour le cas de l'elimination Directe on ne sort que le dernier match de notre arbre
        ArrayList<ElementConfrontation> match = poule.getMatchs(); //On recupere dans le cas de l'elimination directe notre seul match, qui est notre finale

        //On demande les resultats par console de notre arbre
        match.get(0).setConsoleResultat(this.Type); //Arborescence de demande de resultats depuis notre finale
        match.get(0).affichageConsoleResultat(); //On reaffiche ce qui a été rentré (résumé)

        //Mise en place de notre liste d'equipe sortante qui est ici a 1
        ArrayList<Equipe> equipeSortante = new ArrayList<>();
        equipeSortante.add(match.get(0).accesResultat().getEquipeVictoire());
        return equipeSortante;
    }

    //Calcule le resultat d'une phase elimination directe
    @Override
    public void calculateResultPhase(Phase phase) {
        ArrayList<Poule> poules = phase.getPoules(); //notre liste de poules dans la phase
        ArrayList<Equipe> equipesSortantes = new ArrayList<>(); //notre liste d'equipes sortantes

        //On demande les resultats pour chaque poule
        for(int indexPoule = 0; indexPoule < poules.size(); indexPoule++){
            equipesSortantes.addAll(calculateResultPoule(poules.get(indexPoule), 1));
        }

        //On affiche toutes les equipes sortantes de la phase en console
        System.out.println("Resultats donnés sur la phase " + phase.getNumero() + " : " + phase.getNom());
        this.affichageConsoleResultatPhase(equipesSortantes);

        //On ressort toutes les equipes sortantes de la phase
        this.ResultatEquipes = equipesSortantes;
    }
}
