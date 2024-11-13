package Main;

import Facade.CompetitionFacade;
import GestionCompetition.GestionConsoleCompetition;

public class MainTestDeuxPhases {
    //Permet de créer et lancer le code pour une competition avec une premiere phase Round Robi puis une phase Elemination directe
    public static void main(String[] args){
        //Lien à notre facade code
        CompetitionFacade facade = new CompetitionFacade();

        //Notre gestion de console
        GestionConsoleCompetition console = new GestionConsoleCompetition(facade);

        //Creation d'une nouvelle competition
        console.newCompetitionPlusieursPhases();
    }
}
