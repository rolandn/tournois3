package classesMetier;

public class Rencontres
{
    private int Idr;
    private String Phase;
    private int NumEquipe1;
    private int NumEquipe2;
    private int NumArbitre;
    private int NumTable;
    private int NumGagnant;
    private String Score;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIdr() { return Idr; }

    public String getPhase() { return Phase; }

    public int getNumEquipe1() {return NumEquipe1; }

    public int getNumEquipe2() {return NumEquipe2; }

    public int getNumArbitre() {return NumArbitre; }

    public int getNumTable() {return NumTable; }

    public int getNumGagnant() {return NumGagnant; }

    public String getScore() { return Score; }

    @Override
    public String toString() { return Idr + " " + Phase; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIdr(int idr) { Idr = idr; }

    public void setPhase(String phase) { Phase = phase; }

    public void setNumEquipe1(int numEquipe1) {NumEquipe1 = numEquipe1;}

    public void setNumEquipe2(int numEquipe2) {NumEquipe2 = numEquipe2;}

    public void setNumArbitre(int numArbitre) {NumArbitre = numArbitre;}

    public void setNumTable(int numTable) {NumTable = numTable;}

    public void setNumGagnant(int numGagnant) {NumGagnant = numGagnant;}

    public void setScore(String score) { Score = score; }

    /**
     * constructeurs
     */
    public Rencontres()
    {
    }
    public Rencontres(Rencontres renc)
    {
        Idr = renc.Idr;
        Phase = renc.Phase;
        NumEquipe1 = renc.NumEquipe1;
        NumEquipe2 = renc.NumEquipe2;
        NumTable = renc.NumTable;
        NumArbitre = renc.NumTable;
        NumGagnant = renc.NumGagnant;
        Score = renc.Score;

    }
    public Rencontres(int idr, String phase,
                      int numEquipe1, int numEquipe2, int numTable, int numArbitre, int numGagnant, String score)
    {
        Idr = idr;
        Phase = phase;
        NumEquipe1 = numEquipe1;
        NumEquipe2 = numEquipe2;
        NumTable = numTable;
        NumArbitre = numArbitre;
        NumGagnant = numGagnant;
        Score = score;
    }
}

