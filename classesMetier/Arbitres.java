package classesMetier;

import java.sql.PreparedStatement;

public class Arbitres
{
    private int Ida;
    private String Nom;
    private String Prenom;
    private int Experience;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIda() { return Ida; }

    public String getNom() { return Nom; }

    public String getPrenom() { return Prenom; }

    public int getExperience() {return Experience; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIda(int ida) { Ida = ida; }

    public void setNom(String nom) { Nom = nom; }

    public void setPrenom(String prenom) { Prenom = prenom; }

    public void setExperience(int experience) {Experience = experience;}

    /**
     * constructeurs
     */
    public Arbitres()
    {
    }
    public Arbitres(Arbitres arbi)
    {
        Ida = arbi.Ida;
        Nom = arbi.Nom;
        Prenom = arbi.Prenom;
        Experience = arbi.Experience;

    }
    public Arbitres(int ida, String nom,
                      String prenom, int experience)
    {
        Ida = ida;
        Nom = nom;
        Prenom = prenom;
        Experience = experience;
    }
}

