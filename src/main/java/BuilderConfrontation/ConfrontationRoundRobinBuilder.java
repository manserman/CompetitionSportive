package BuilderConfrontation;
import Component.CompositeArbre.ElementConfrontation;
import Component.CompositeArbre.MatchInitial;
import Component.Poule;
import Component.Equipe;
import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;
import java.util.Scanner;

public class ConfrontationRoundRobinBuilder extends ConfrontationBuilder{
    //Appel de création du manager de type RoundRobin
    @Override
    public void createNewConfrontation() {
        managerConfrontation = ManagerConfrontation.getManager(ManagerConfrontation.ConfrontationType.RoundRobin);
    }

    @Override
    public ArrayList<Equipe> buildEquipe() {
        //Liste des equipes crees pour cette poule
        ArrayList<Equipe> equipesPoules= new ArrayList<>();// equipes de la poule;

        //implémentation console
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------");

        int nombreEquipes;
        //Demande du nombre d'equipe
        do {
            System.out.print("Nombre d'équipes à créer dans la poule RoundRobin (>=3): ");
            nombreEquipes = scanner.nextInt();
            scanner.nextLine(); // Lire la fin de ligne après avoir lu l'entier
        } while (nombreEquipes < 3);

        System.out.println("---------------------------");

        //Creation de la liste des equipes en fonction du nombre precedement rentre
        for (int i = 0; i < nombreEquipes; i++) {
            System.out.print("Nom de l'équipe " + (i + 1) + " : ");
            String nomEquipe = scanner.nextLine();
            Equipe equipe = new Equipe(nomEquipe);
            equipesPoules.add(equipe);
            //On maj les equipes présentes dans la phase
            this.equipes.add(equipe);
        }

        //On retourne les equipes de la poule actuelle
        return equipesPoules;
    }

    @Override
    protected void buildPouleWithEquipe(ArrayList<ArrayList<Equipe>> Equipes){
        int indexPoule = 1;
        //Chaque poule est détaillée par une itération ArrayList<Equipe> de notre liste Equipes
        for (ArrayList<Equipe> listeEquipePoule : Equipes) {
            //On ajoute la liste des equipes de cette poule
            this.equipes.addAll(listeEquipePoule);

            int nbrEquipe = listeEquipePoule.size();
            //On vérifie que la taille soit adéquate
            if(nbrEquipe>2){
                //Génération des matchs avec sa poule
                this.constructMatchPoule(listeEquipePoule, indexPoule);
                //On passe à la poule suivante
                indexPoule++;
            }
            else
                // Arrêt de la boucle actuelle avec une exception
                throw new RuntimeException("La Poule ne peut pas faire un RoundRobin avec un nombre d'equipe inférieur ou égal à 2.");
        }
    }

    @Override
    protected void buildPouleWithoutEquipe(int nbrPoules){
        ArrayList<Equipe> equipesPoules; // equipes de la poule;

        //creation de nos poules
        for(int i=0;i<nbrPoules; i++){
            //Génération des equipes pour la poule
            equipesPoules = buildEquipe();
            //Génération des matchs avec sa poule
            this.constructMatchPoule(equipesPoules, i+1);
        }
    }

    private void constructMatchPoule(ArrayList<Equipe> equipesPoules, int numPoule) {
        ArrayList<ElementConfrontation> matchs = new ArrayList<>(); // matchs de la poule;
        //Génération des matchs pour chaque poule - boucle pour faire un nbrMatch = nbrEquipe!
        System.out.println("---------------------------");
        System.out.println("Liste des matchs générés :");
        for(int indexEquipeUn = 0; indexEquipeUn < equipesPoules.size() - 1; indexEquipeUn++)
        {
            for(int indexEquipeDeux= indexEquipeUn+1; indexEquipeDeux < equipesPoules.size(); indexEquipeDeux++){
                System.out.print("Equipe "+ indexEquipeUn +"-"+equipesPoules.get(indexEquipeUn).getNom() +" vs ");
                System.out.println("Equipe "+ indexEquipeDeux +"-"+equipesPoules.get(indexEquipeDeux).getNom());
                matchs.add(new MatchInitial(null,null, equipesPoules.get(indexEquipeUn),equipesPoules.get(indexEquipeDeux)));
            }
        }
        this.poules.add(new Poule("Poule "+ numPoule, matchs, equipesPoules));// Ajout de la poule à la phase
    }
}
