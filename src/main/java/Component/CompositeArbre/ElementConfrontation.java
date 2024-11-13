package Component.CompositeArbre;

import Component.Equipe;
import Component.Resultat;
import ManagerFactory.ManagerConfrontation;

import java.util.Scanner;

public abstract class ElementConfrontation {

    protected String date;
    protected Resultat resultat;

    public ElementConfrontation(String date, Resultat resultat) {
        this.setDate(date);
        this.setResultat(resultat);
    }

    public abstract void affichageConsoleResultat();

    public abstract void setConsoleResultat(ManagerConfrontation.ConfrontationType type);

    public abstract Resultat accesResultat();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    protected void askForDate(){
        //on demande les informations sur la date du match
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la date du match (sous forme dd/mm/aaaa-hh) : ");
        String dateFournie = scanner.nextLine();
        this.setDate(dateFournie);
    }

    protected void askForResultat(Equipe equipeA, Equipe equipeB, ManagerConfrontation.ConfrontationType type){
        int scoreEquipeA;
        int scoreEquipeB;
        //Ici on a fait une gestion du nul non polyvalente et non modulaire a cause de l'affichage et entree console, avec le CRUD, il est facile de forcer et verifier les cas ou il n'y a pas de nul
        do{
            //pour la premiere equipe
            Scanner scanner = new Scanner(System.in);
            System.out.print("Score de l'equipe A - " +  equipeA.getNom() + " : ");
            scoreEquipeA = scanner.nextInt();
            scanner.nextLine(); // Lire la fin de ligne après avoir lu l'entier
            equipeA.ajoutScore(scoreEquipeA);

            //pour la deuxieme equipe
            System.out.print("Score de l'equipe B - " + equipeB.getNom() +" :");
            scoreEquipeB = scanner.nextInt();
            scanner.nextLine(); // Lire la fin de ligne après avoir lu l'entier
            equipeB.ajoutScore(scoreEquipeB);

            if(type == ManagerConfrontation.ConfrontationType.EliminationDirecte && scoreEquipeA == scoreEquipeB){
                System.out.println("Pas de nul pour l'elimination directe ");
            }
        }while(type == ManagerConfrontation.ConfrontationType.EliminationDirecte && scoreEquipeA == scoreEquipeB);


        //On calcule le resultat du match
        //Cas vistoire A
        if(scoreEquipeA > scoreEquipeB){
            equipeA.ajoutVictoire();
            equipeB.ajoutDefaite();
            this.setResultat(new Resultat(Resultat.TypeResultat.VICTOIRE,equipeA,equipeB,scoreEquipeA,scoreEquipeB));
        }
        //Cas victoire B
        else if (scoreEquipeA < scoreEquipeB){
            equipeA.ajoutDefaite();
            equipeB.ajoutVictoire();
            this.setResultat(new Resultat(Resultat.TypeResultat.VICTOIRE,equipeB,equipeA,scoreEquipeB,scoreEquipeA));
        }
        //Cas nul
        else{
            equipeA.ajoutNul();
            equipeB.ajoutNul();
            this.setResultat(new Resultat(Resultat.TypeResultat.NUL,equipeA,equipeB,scoreEquipeA,scoreEquipeB));
        }
    }
}