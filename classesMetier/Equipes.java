package classesMetier;

import java.sql.PreparedStatement;

public class Equipes
{
    private int Ide;
    private String Nom;
    private int Joueur1;
    private int Joueur2;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIde() { return Ide; }

    public String getNom() { return Nom; }

    public int getJoueur1() {return Joueur1; }

    public int getJoueur2() {return Joueur2; }

    @Override
    public String toString() { return Ide + " " + Nom; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIde(int ide) { Ide = ide; }

    public void setNom(String nom) { Nom = nom; }

    public void setJoueur1(int joueur1) {Joueur1 = joueur1;}

    public void setJoueur2(int joueur2) {Joueur2 = joueur2;}

    /**
     * constructeurs
     */
    public Equipes()
    {
    }
    public Equipes(Equipes equi)
    {
        Ide = equi.Ide;
        Nom = equi.Nom;
        Joueur1 = equi.Joueur1;
        Joueur2 = equi.Joueur2;

    }
    public Equipes(int ide, String nom,
                    int joueur1, int joueur2)
    {
        Ide = ide;
        Nom = nom;
        Joueur1 = joueur1;
        Joueur2 = joueur2;
    }
}

