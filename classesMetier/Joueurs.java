package classesMetier;

import java.sql.PreparedStatement;

public class Joueurs
{
    private int Idj;
    private String Nom;
    private String Prenom;
    private String Style;
    private String NomImage;
    /**
     * propriété get... pour obtenir la valeur des attributs
     */
    public int getIdj() { return Idj; }

    public String getNom() { return Nom; }

    public String getPrenom() { return Prenom; }

    public String getStyle() {return Style; }

    public String getNomImage() { return NomImage; }

    /**
     * propriété set... pour modifier la valeur des attributs
     */

    public void setIdj(int idj) { Idj = idj; }

    public void setNom(String nom) { Nom = nom; }

    public void setPrenom(String prenom) { Prenom = prenom; }

    public void setStyle(String style) {Style = style;}

    public void setNomImage(String nom) { NomImage = nom; }


    /**
     * redéfinition de la méthode toString
     * @return la concaténation du nom et du nom du joueur
     */
    @Override
    public String toString() { return Nom; }

    /**
     * constructeurs
     */
    public Joueurs()
    {
    }
    public Joueurs(Joueurs jou)
    {
        Idj = jou.Idj;
        Nom = jou.Nom;
        Prenom = jou.Prenom;
        Style = jou.Style;
        NomImage = jou.NomImage;

    }
    public Joueurs(int idj, String nom,
                    String prenom, String style, String nomImage)
    {
        Idj = idj;
        Nom = nom;
        Prenom = prenom;
        Style = style;
        NomImage = nomImage;
    }
}


