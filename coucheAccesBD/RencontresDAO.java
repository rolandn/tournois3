package coucheAccesBD;

import java.text.DecimalFormat;
import java.util.Random;
import classesMetier.*;
import controleurs.ListerArbitres;
import controleurs.ListerTables;
import controleurs.ListerRencontres;
import controleurs.ListerEquipes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RencontresDAO extends BaseDAO<Rencontres>
{
    public RencontresDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de toutes les rencontres
     */

    public List<Rencontres> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Rencontres> liste = new ArrayList<Rencontres>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select idr," +
                    " phase, NumEquipe1, NumEquipe2, " +
                    "NumArbitre, NumTable, NumGagnant, score " +
                    "from rencontres " +
                    "order by idr asc");

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Rencontres(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getInt(3),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5),
                        sqlRes.getInt(6),
                        sqlRes.getInt(7),
                        sqlRes.getString(8)));
            sqlRes.close();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }

        return liste;
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de toutes les rencontres
     */

    public List<Rencontres> listerRencontresEquipe(int num) throws ExceptionAccesBD
    {
        ArrayList<Rencontres> liste = new ArrayList<Rencontres>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select idr," +
                    " phase, NumEquipe1, NumEquipe2, " +
                    "NumArbitre, NumTable, NumGagnant, score " +
                    "from rencontres " +
                    " where NumEquipe1 = ? OR NumEquipe2 = ?" +
                    "order by idr asc");

            SqlCmd.setInt(1, num);
            SqlCmd.setInt(2, num);

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Rencontres(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getInt(3),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5),
                        sqlRes.getInt(6),
                        sqlRes.getInt(7),
                        sqlRes.getString(8)));
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

    public void ajouter(Rencontres obj) throws ExceptionAccesBD
    {
        try
        {
            SqlCmd = SqlConn.prepareCall("select max(idr) from rencontres");

            ResultSet sqlRes = SqlCmd.executeQuery();
            sqlRes.next();

            int idr = sqlRes.getInt(1);
            if(sqlRes.wasNull()) idr = 1;

            else idr = idr +1;

            SqlCmd.close();

            SqlCmd = SqlConn.prepareCall("insert into rencontres values(?, ?, ?, ?, ?, ?, 0, '')");

            SqlCmd.setInt(1, idr);
            SqlCmd.setString(2, obj.getPhase());
            SqlCmd.setInt(3, obj.getNumEquipe1());
            SqlCmd.setInt(4, obj.getNumEquipe2());
            SqlCmd.setInt(5, obj.getNumArbitre());
            SqlCmd.setInt(6, obj.getNumTable());
        //    SqlCmd.setInt(7, obj.getNumGagnant());
        //    SqlCmd.setString(8, obj.getScore());

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }

    /**
     * méthode qui supprime dans la base de données un joueur
     * @param num : le numéro de la rencontre
     */

    public void supprimer(int num) throws ExceptionAccesBD
    {
        try
        {
            SqlConn.setAutoCommit(false);

            SqlCmd = SqlConn.prepareCall("delete from rencontres where idr = ?");
            SqlCmd.setInt(1, num);

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }

    /**
     * méthode qui modifie dans la base de données une rencontre
     * @param obj : la rencontre
     */

    public void modifier(Rencontres obj) throws ExceptionAccesBD
    {
        try
        {
            SqlCmd = SqlConn.prepareCall("update rencontres " +
                    "set NumGagnant = ?, " +
                    "score = ? " +
                    "where idr = ?");

            SqlCmd.setInt(1, obj.getNumGagnant());
            SqlCmd.setString(2, obj.getScore());
            SqlCmd.setInt(3,obj.getIdr());

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }

}
