package coucheAccesBD;
import java.sql.*;
import java.util.*;
import classesMetier.*;

public class JoueursDAO extends BaseDAO<Joueurs>
{
    public JoueursDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de tous les cours
     */

    public List<Joueurs> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Joueurs> liste = new ArrayList<Joueurs>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select idj, nom, prenom, style, nomImage " +
                    "from joueurs " +
                    "order by nom asc");

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Joueurs(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getString(4),
                        sqlRes.getString(5)));
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
     * @param obj : le joueur
     */

    public void ajouter(Joueurs obj) throws ExceptionAccesBD
    {
        try
        {
            SqlCmd = SqlConn.prepareCall("select max(idj) + 1 from joueurs");

            ResultSet sqlRes = SqlCmd.executeQuery();
            sqlRes.next();

            int idj = sqlRes.getInt(1);
            if(sqlRes.wasNull()) idj = 1;

            else idj = idj +1;

            SqlCmd.close();

            SqlCmd = SqlConn.prepareCall("insert into joueurs values(?, ?, ?, ?, ? ) ");

            SqlCmd.setInt(1, idj);
            SqlCmd.setString(2, obj.getNom());
            SqlCmd.setString(3, obj.getPrenom());
            SqlCmd.setString(4, obj.getStyle());
            SqlCmd.setString(5, obj.getNomImage());

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

            SqlCmd = SqlConn.prepareCall("delete from joueurs where idj = ? ");
            SqlCmd.setInt(1, num);

            SqlCmd.executeUpdate();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }



}
