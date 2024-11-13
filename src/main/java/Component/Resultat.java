package Component;



public class Resultat {
    public enum TypeResultat {
        VICTOIRE, NUL;
    }

    private TypeResultat Type;
    private Equipe EquipeVictoire;
    private Equipe EquipeDefaite;
    private int ScoreEquipeVictoire;
    private int ScoreEquipeDefaite;


    public Resultat(TypeResultat type, Equipe equipeVictoire, Equipe equipeDefaite, int scoreVictoire, int scoreDefaite ) {
        this.Type = type;
        this.EquipeVictoire = equipeVictoire;
        this.EquipeDefaite = equipeDefaite;
        this.ScoreEquipeVictoire = scoreVictoire;
        this.ScoreEquipeDefaite = scoreDefaite;
    }

    public TypeResultat getType() {
        return Type;
    }

    public Equipe getEquipeVictoire() {
        return EquipeVictoire;
    }

    public Equipe getEquipeDefaite() {
        return EquipeDefaite;
    }

    public int getScoreEquipeVictoire() {
        return ScoreEquipeVictoire;
    }

    public int getScoreEquipeDefaite() {
        return ScoreEquipeDefaite;
    }

    public String affichage(){
        if(this.Type == TypeResultat.VICTOIRE)
            return "Victoire " + EquipeVictoire.getNom() + " sur un score de " + getScoreEquipeVictoire() + " - " + getScoreEquipeDefaite();
        else
            return "Nul";
    }
}
