package GestionCompetition;

import BuilderConfrontation.ConfrontationBuilder;
import BuilderConfrontation.ConfrontationEliminationDirecteBuilder;
import BuilderConfrontation.ConfrontationRoundRobinBuilder;
import Component.Competition;
import Component.Equipe;
import Component.Phase;
import Facade.CompetitionFacade;
import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionConsoleCompetition {

    private CompetitionFacade facade;

    public GestionConsoleCompetition(CompetitionFacade f){
        this.facade = f;
    }

    public void menu(int i){
        //Menu à modifier pour le choix de la confrontation
        System.out.println("Mode de confrontationpour la phase "+ i +" :");
        System.out.println("1. EliminationDirecte");
        System.out.println("2. RoundRobin");
        System.out.print("Choix : ");
    }

    public ConfrontationBuilder choix(int choisi){
        //Liste  modifier pour la liste des modes de confronation
        if (choisi == 1) {
            return new ConfrontationEliminationDirecteBuilder();
        } else if (choisi == 2) {
            return new ConfrontationRoundRobinBuilder();
        } else {
            System.out.println("Choix invalide.");
            return null;
        }
    }

    public void newCompetitionUnePhase(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("!!!!! Pas de regle d'egalite pour le round robin, on prend dans l'ordre des victoires puis des points, donc si egalite, on prend selon le tri et pas autrement!!!!!");
        System.out.println("!!!!! Parfois il faut faire plusieurs enter pour passer a la suite !!!!!");

        //Demande du nom de la competition
        System.out.print("Nom de la competition: ");
        String nomCompetition = scanner.nextLine();

        //Demande de la categorie de la competition
        System.out.print("Category de la competition: ");
        String nomCategory = scanner.nextLine();

        System.out.println("---------------------------");

        //Creation de notre competition
        Competition competition = facade.createCompetition(nomCompetition, nomCategory);

        //Recherche du mode de competition
        ConfrontationBuilder modeConfrontation;
        do {
            System.out.println("!!!!!! Appuyez deux fois sur enter sur la prochaine donnée fournie: !!!!!!");
            // Afficher le menu pour le mode de confrontation
            menu(1);

            //Lecture du choix
            int choixConfrontation = scanner.nextInt();
            scanner.nextLine();

            //Correspondance du choix
            modeConfrontation = choix(choixConfrontation);

        } while (modeConfrontation == null);

        System.out.println("---------------------------");
        System.out.println("Appuyez sur enter");

        //Creation de la phase correspondante
        Phase phase = facade.createManagerConfrontationPhase(competition, modeConfrontation, new ArrayList<>());

        //On ajoute a notre liste de competition, la competition cree
        facade.addCompetiton(competition);

        //Demande des résultats
        facade.resultatPhase(phase);
    }

    public void newCompetitionPlusieursPhases(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("!!!!! Parfois il faut enter pour passer a la suite !!!!!");

        //Demande du nom de la competition
        System.out.print("Nom de la competition: ");
        String nomCompetition = scanner.nextLine();

        //Demande de la categorie de la competition
        System.out.print("Category de la competition: ");
        String nomCategory = scanner.nextLine();

        //Creation de notre competition
        Competition competition = facade.createCompetition(nomCompetition, nomCategory);

        // Les différentes phases à notre competition
        //Phase Round Robin
            ArrayList<ArrayList<Equipe>> equipesResultantes = new ArrayList<>();

            //Creation de la phase RoundRobin correspondante avec au moins 5 equipes pour le test
            System.out.print("!!!!!!! Lors de la demande du nombre de poules, mettre a 1 !!!!!!!!");
            System.out.print("!!!!!!! Lors de la demande du nombre d'equipes, mettre au moins 5 !!!!!!!!");
            Phase phaseUne = facade.createManagerConfrontationPhase(competition, new ConfrontationRoundRobinBuilder(), equipesResultantes);

            //On ajoute a notre liste de competition, la competition cree
            facade.addCompetiton(competition);

            //Demande des résultats avec en sortie 4 equipes
            System.out.print("!!!!!!! Lors de la demande d'equipes sortantes, mettre 4 !!!!!!!!");
            facade.resultatPhase(phaseUne);

            //On recupere le resultat avec en sortie les 4 equipes
            equipesResultantes.add(facade.getResultatPhase(phaseUne));
            System.out.println(equipesResultantes);

        //Phase Eliminitation directe de 4 equipes - 3 matchs
            //Creation de la phase Elimination directe correspondante avec 4 equipes
            Phase phaseDeux = facade.createManagerConfrontationPhase(competition, new ConfrontationEliminationDirecteBuilder(), equipesResultantes);

            //Demande des résultats avec en sortie l'equipe gagnante
            facade.resultatPhase(phaseDeux);
    }
}
