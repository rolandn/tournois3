package coucheAccesBD;
import java.sql.*;
import java.util.*;
import classesMetier.*;

public class ArbitreDAO extends BaseDAO<Arbitres>
{
    public ArbitreDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de tous les cours
     */

    public List<Arbitres> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Arbitres> liste = new ArrayList<Arbitres>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select ida, nom, prenom, experience " +
                    "from arbitres " +
                    "order by nom asc");

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Arbitres(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
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
