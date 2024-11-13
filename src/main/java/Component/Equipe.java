package Component;

public class Equipe {
    private String Nom;
    private int NombreVictoire;
    private int NombreDefaite;
    private int NombreNul;
    private int NombrePoint;

    public Equipe(String nomEquipe) {
        this.Nom = nomEquipe;
        this.NombreDefaite = this.NombreNul = this.NombreVictoire  = this.NombrePoint = 0;
    }

    public String getNom(){
        return this.Nom;
    }

    public int getNombreVictoire() {
        return NombreVictoire;
    }

    public int getNombreDefaite() {
        return NombreDefaite;
    }

    public int getNombreNul() {
        return NombreNul;
    }

    public int getNombrePoint() {
        return NombrePoint;
    }

    public void ajoutVictoire(){
        this.NombreVictoire++;
    }

    public void ajoutDefaite(){
        this.NombreDefaite++;
    }

    public void ajoutNul(){
        this.NombreNul++;
    }

    public void ajoutScore(int score){
        this.NombrePoint += score;
    }

    public void affichageConsole() {
            System.out.println("---------------------------");
            System.out.println("Nom : " + getNom());
            System.out.println("Nombre de victoires : " + getNombreVictoire());
            System.out.println("Nombre de défaites : " + getNombreDefaite());
            System.out.println("Nombre de matchs nuls : " + getNombreNul());
            System.out.println("Nombre de points : " + getNombrePoint());
    }
}
