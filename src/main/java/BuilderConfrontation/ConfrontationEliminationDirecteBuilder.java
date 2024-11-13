package BuilderConfrontation;

import Component.CompositeArbre.ElementConfrontation;
import Component.CompositeArbre.MatchInitial;
import Component.CompositeArbre.MatchIntermediaire;
import Component.Equipe;
import Component.Poule;
import ManagerFactory.ManagerConfrontation;

import java.util.ArrayList;
import java.util.Scanner;

public class ConfrontationEliminationDirecteBuilder extends ConfrontationBuilder {

    //Appel de création du manager de type EliminationDirecte
    @Override
    public void createNewConfrontation() {
        managerConfrontation = ManagerConfrontation.getManager(ManagerConfrontation.ConfrontationType.EliminationDirecte);
    }

    @Override
    protected ArrayList<Equipe> buildEquipe() {
        //Liste des equipes crees pour cette poule
        ArrayList<Equipe> equipesPoules= new ArrayList<>();// equipes de la poule;

        //implémentation console
        Scanner scanner = new Scanner(System.in);

        //Cherche le nombre d'equipe de la phase qui doit etre forcement une puissance de 2
        int nombreEquipes = 0;
        boolean estPuissanceDeDeux = false;

        System.out.println("---------------------------");

        while (!estPuissanceDeDeux) {
            System.out.print("Entrez le nombre d'équipes (puissance de 2) : ");
            nombreEquipes = scanner.nextInt();
            scanner.nextLine();

            if (estPuissanceDeDeux(nombreEquipes)) {
                estPuissanceDeDeux = true;
            } else {
                System.out.println("Le nombre d'équipes doit être une puissance de 2. Veuillez réessayer.");
            }
        }

        System.out.println("---------------------------");

        //La liste se trouve etre dans l'ordre de création des matchs
        System.out.println("Veuillez fournir les equipes dans le sens des rencontres.");

        //Creation de la liste des equipes en fonction du nombre precedement rentre
        for (int i = 0; i < nombreEquipes; i++) {
            if(i%2==0)
                System.out.print("Nom de la premiere équipe du match " + (i/2 + 1) + " : ");
            else
                System.out.print("Nom de l'équipe adverse du match " + (i/2 + 1) + " : ");
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
    protected void buildPouleWithEquipe(ArrayList<ArrayList<Equipe>> Equipes) {
        int indexPoule = 1;
        System.out.print("Mise en place de la poule Elimination Directe");
        System.out.println("---------------------------");
        //Chaque poule est détaillée par une itération ArrayList<Equipe> de notre liste Equipes
        for (ArrayList<Equipe> listeEquipePoule : Equipes) {
            //On ajoute la liste des equipes de cette poule
            this.equipes.addAll(listeEquipePoule);

            int nbrEquipe = listeEquipePoule.size();
            //On vérifie que la taille soit adéquate
            if(estPuissanceDeDeux(nbrEquipe)){
                //Génération des matchs avec sa poule
                this.constructMatchPoule(listeEquipePoule, indexPoule);
                //On passe à la poule suivante
                indexPoule++;
            }
            else
                // Arrêt de la boucle actuelle avec une exception
                throw new RuntimeException("La Poule ne peut pas faire une Elimination Directe avec un nombre qui n'est pas une puissance de 2.");
        }
    }

    @Override
    protected void buildPouleWithoutEquipe(int nbrPoules) {
        ArrayList<Equipe> equipesPoules; // equipes de la poule;

        //creation de nos poules
        for(int i=0;i<nbrPoules; i++){
            System.out.println("---------------------------");
            System.out.print("Information Poule n°" + (i+1) + " : ");
            System.out.println("---------------------------");
            //Génération des equipes pour la poule
            equipesPoules = buildEquipe();
            //Génération des matchs avec sa poule
            this.constructMatchPoule(equipesPoules, i+1);
        }
    }

    private void constructMatchPoule(ArrayList<Equipe> equipesPoules, int numPoule){
        ArrayList<ElementConfrontation> matchs = new ArrayList<>(); // matchs de la poule;
        //Génération des matchs pour chaque poule - un arbre de match qui se présente!

        //Generation des matchs initiaux
        for(int indexEquipeUn = 0; indexEquipeUn <= equipesPoules.size() - 1; indexEquipeUn += 2)
        {
           matchs.add(new MatchInitial(null, null, equipesPoules.get(indexEquipeUn), equipesPoules.get(indexEquipeUn+1)));
        }

        //Generation des matchs intermédiaires - Jusqu'a ce qu'on que un match restant (finale)
        while(matchs.size()>1){
            matchs = constructMatchInter(matchs);
        }

        //On crée la poule correspondante
        this.poules.add(new Poule("Poule "+ numPoule, matchs, equipesPoules));// Ajout de la poule à la phase
    }

    private ArrayList<ElementConfrontation> constructMatchInter(ArrayList<ElementConfrontation> ListeMatchs){
        ArrayList<ElementConfrontation> ListeMatchsInter = new ArrayList<>(); //Notre lisyte de matchs intermédiaires

        //Creation de tous les matchs intermediaires qui decoulent de la liste en parametre
        for(int indexEquipeUn = 0; indexEquipeUn <= ListeMatchs.size() - 1; indexEquipeUn += 2){
            ListeMatchsInter.add(new MatchIntermediaire(null, null, ListeMatchs.get(indexEquipeUn),ListeMatchs.get(indexEquipeUn+1)));
        }
        return ListeMatchsInter;
    }

    private static boolean estPuissanceDeDeux(int nombre) {
        return (nombre & (nombre - 1)) == 0 && nombre > 0;
    }
}
