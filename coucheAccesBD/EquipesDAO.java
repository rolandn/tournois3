package coucheAccesBD;
import java.sql.*;
import java.util.*;
import classesMetier.*;

public class EquipesDAO extends BaseDAO<Equipes>
{
    public EquipesDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de tous les cours
     */

    public List<Equipes> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Equipes> liste = new ArrayList<Equipes>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select ide, nom, joueur1, joueur2 " +
                    "from equipe " +
                    "order by ide asc ");

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Equipes(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getInt(3),
                        sqlRes.getInt(4)));
            sqlRes.close();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }

        return liste;
    }

    /**
     * méthode qui ajoute dans la base de données un joueur
     * @param obj : l'équipe
     */

    public void ajouter(Equipes obj) throws ExceptionAccesBD
    {
        try
        {
            SqlCmd = SqlConn.prepareCall("select max(ide) + 1 from equipe");

            ResultSet sqlRes = SqlCmd.executeQuery();
            sqlRes.next();

            int ide = sqlRes.getInt(1);
            if(sqlRes.wasNull()) ide = 1;

            else ide = ide +1;

            SqlCmd.close();

            SqlCmd = SqlConn.prepareCall("insert into equipe values(?, ?, ?, ?)");

            SqlCmd.setInt(1, ide);
            SqlCmd.setString(2, obj.getNom());
            SqlCmd.setInt(3, obj.getJoueur1());
            SqlCmd.setInt(4, obj.getJoueur2());

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }

    /**
     * méthode qui supprime dans la base de données un joueur
     * @param num : le numéro du joueur
     */

    public void supprimer(int num) throws ExceptionAccesBD
    {
        try
        {
            SqlConn.setAutoCommit(false);

            SqlCmd = SqlConn.prepareCall("delete from equipe where ide = ? ");
            SqlCmd.setInt(1, num);

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }

    public void supprimerviajoueur(int idj) throws ExceptionAccesBD
    {
        try
        {
            SqlConn.setAutoCommit(false);

            SqlCmd = SqlConn.prepareCall("delete from equipe where Joueur1 = ? OR Joueur2 = ? ");
            SqlCmd.setInt(1, idj);
            SqlCmd.setInt(2, idj);

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }


    public List<Equipes> chercherEquipe(int idj) throws ExceptionAccesBD
    {
        ArrayList<Equipes> liste = new ArrayList<Equipes>();
        try
        {
            SqlConn.setAutoCommit(false);

            SqlCmd = SqlConn.prepareCall("select ide, nom, joueur1, joueur2 from equipe where joueur1 = ? OR joueur2 = ? ");
            SqlCmd.setInt(1, idj);
            SqlCmd.setInt(2, idj);
            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Equipes(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getInt(3),
                        sqlRes.getInt(4)));
            sqlRes.close();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
        return liste;
    }



}
