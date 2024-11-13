package ManagerFactory;

import Component.CompositeArbre.ElementConfrontation;
import Component.Equipe;
import Component.Phase;
import Component.Poule;

import java.util.*;

public class ManagerConfrontationRoundRobin extends ManagerConfrontation {

    public ManagerConfrontationRoundRobin(){
        this.Type = ConfrontationType.RoundRobin;
    }

    //Calcule le resultat d'une poule de phase round robin
    @Override
    public ArrayList<Equipe> calculateResultPoule(Poule poule,  int nbrEquipeSortante) {
        ArrayList<Equipe> equipes= poule.getListesEquipes();
        ArrayList<ElementConfrontation> matchs = poule.getMatchs(); //Liste de nos matchs

        // On demande les resultats
        for(ElementConfrontation match : matchs){
            //Demande resultat du match
            match.setConsoleResultat(this.Type);
        }
        // Trie le tableau d'équipes en fonction de victoire puis des points (ordre décroissant)
        Collections.sort(
                equipes,
                Comparator.comparingInt((Equipe equipe) -> equipe.getNombreVictoire()).reversed()
                        .thenComparingInt((Equipe equipe) -> equipe.getNombrePoint()).reversed()
        );

        ArrayList<Equipe> equipesSortantes= new ArrayList<>(); //Liste des equipes sortantes
        //On prend que les equipes sortantes, on prend pas en compte dans cette version de compte l'egalite de roundRobin
        for(int indexEquipe = 0; indexEquipe < nbrEquipeSortante; indexEquipe++){
            equipesSortantes.add(equipes.get(indexEquipe));
        }
        return equipesSortantes;
    }

    //Calcule le resultat d'une phase elimination directe
    @Override
    public void calculateResultPhase(Phase phase) {
        ArrayList<Equipe> equipesSortantes = new ArrayList<>(); //Liste de nos equipesSortantes de notre phase

        //implémentation console
        Scanner scanner = new Scanner(System.in);
        for(Poule poule: phase.getPoules())
        {
            System.out.println("---------------------------");
            int nombreEquipesSortantes;
            //On demande le nombre d'equipes sortantes de la poule
            do {
                System.out.print("Nombre d'équipes sortantes de la " + poule.getNom() + " (nbrEquipesSortantes >= 1 et nbrEquipesSortantes <= " + (poule.getListesEquipes().size()-1) + ") : ");
                nombreEquipesSortantes = scanner.nextInt();
                scanner.nextLine(); // Lire la fin de ligne après avoir lu l'entier
            } while (nombreEquipesSortantes < 1 && nombreEquipesSortantes > (poule.getListesEquipes().size()-1));

            //On lance le resultat sur la poule
            equipesSortantes.addAll(calculateResultPoule(poule, nombreEquipesSortantes));
        }

        //On affiche toutes les equipes sortantes de la phase en console
        this.affichageConsoleResultatPhase(equipesSortantes);

        //On ressort toutes les equipes sortantes de la phase dans notre variable
        this.ResultatEquipes = equipesSortantes;
    }
}
