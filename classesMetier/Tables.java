package classesMetier;

import javafx.geometry.Pos;

import java.sql.PreparedStatement;

public class Tables
{
    private int Idt;
    private int Position;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIdt() { return Idt; }
    public int getPosition() {return Position; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIdt(int idt) { Idt = idt; }
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
        Position = tab.Position;

    }
    public Tables(int idt, int position)
    {
        Idt = idt;
        Position = position;
    }
}


