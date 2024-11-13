package Main;

import Facade.CompetitionFacade;
import GestionCompetition.GestionConsoleCompetition;

public class MainCompetitionUnePhase {
    //Permet de créer et lancer le code pour une competition
    public static void main(String[] args){
        //Lien à notre facade code
        CompetitionFacade facade = new CompetitionFacade();

        //Notre gestion de console
        GestionConsoleCompetition console = new GestionConsoleCompetition(facade);

        //Creation d'une nouvelle competition
        console.newCompetitionUnePhase();
    }
}
