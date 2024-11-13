package BuilderConfrontation;

import Component.Competition;
import Component.Equipe;
import Component.Phase;
import Component.Poule;
import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ConfrontationBuilder {
    protected ArrayList<Equipe> equipes = new ArrayList<>();
    protected ArrayList<Poule> poules = new ArrayList<>();
    protected ManagerConfrontation managerConfrontation;

    protected Phase phase;

    public ManagerConfrontation getManagerConfrontation(){
        return managerConfrontation;
    }

    public abstract void createNewConfrontation();

    protected abstract ArrayList<Equipe> buildEquipe();

    //Construit notre poule avec des equipes deja créées
    protected abstract void buildPouleWithEquipe(ArrayList<ArrayList<Equipe>> Equipes);

    //Construit une poule sans equipe de base
    protected abstract void buildPouleWithoutEquipe(int NbrPoules);

    //Initialise le meme buildPoule peut importe le mode de confrontation
    protected void buildPoule(ArrayList<ArrayList<Equipe>> Equipes){
        //Nombre de poule
        int nbrPoule;
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("---------------------------");

        //on construit les equipes necessaires si on en a pas
        if(Equipes.size() == 0) {
            //Demande du nombre de poule
            System.out.print("Nombre de poules dans la phase: ");
            nbrPoule = scanner.nextInt();
            while(nbrPoule < 1){
                System.out.print("Veuillez sairi un nombre superieur ou egal a 1. ");
                nbrPoule = scanner.nextInt();
                scanner.nextLine();
            }
            buildPouleWithoutEquipe(nbrPoule);
        }
        else
            buildPouleWithEquipe(Equipes);
    };

    //Initialise le meme buildPhase peut importe le mode de confrontation
    public void buildPhase(Competition competition, ArrayList<ArrayList<Equipe>> Equipes){
        //on créer les différentes poules de notre phase
        buildPoule(Equipes);

        System.out.println("---------------------------");

        //on demande les informations sur la phase
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de la phase: ");
        String nomPhase = scanner.nextLine();

        System.out.print("Entrez le numéro de la phase : ");
        int numeroPhase = scanner.nextInt();

        //Instanciation de notre phase
        this.phase= new Phase(numeroPhase,nomPhase,this.poules);

        //creation de notre managerConfrontation si il est null
        if (managerConfrontation == null) {
            this.createNewConfrontation();
        }

        //Impute notre phase dans notre manager
        managerConfrontation.setPhase(this.phase);

        //Impute notre manager dans la phase
        phase.setManagerConfrontation(this.getManagerConfrontation());

        //Impute notre phase dans notre competition
        competition.addPhase(this.phase);
    }
}
