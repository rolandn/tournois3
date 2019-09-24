package classesMetier;

import javafx.geometry.Pos;

import java.sql.PreparedStatement;

public class Tables
{
    private int Idt;
    private String Nom;
    private int Position;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIda() { return Idt; }

    public String getNom() { return Nom; }

    public int getPosition() {return Position; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIdt(int idt) { Idt = idt; }

    public void setNom(String nom) { Nom = nom; }

    public void setPosition(int position) {Position = position;}

    /**
     * constructeurs
     */
    public Tables()
    {
    }
    public Tables(Tables tab)
    {
        Idt = tab.Idt;
        Nom = tab.Nom;
        Position = tab.Position;

    }
    public Tables(int idt, String nom, int position)
    {
        Idt = idt;
        Nom = nom;
        Position = position;
    }
}


