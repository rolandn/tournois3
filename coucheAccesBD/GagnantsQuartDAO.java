package coucheAccesBD;
import java.sql.*;
import java.util.*;
import classesMetier.*;

public class GagnantsQuartDAO extends BaseDAO<Rencontres>
{
    public GagnantsQuartDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de tous les cours
     */

    public List<Rencontres> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Rencontres> liste = new ArrayList<Rencontres>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select idr," +
                    " phase, NumEquipe1, NumEquipe2, " +
                    "NumArbitre, NumTable, NumGagnant, score " +
                    "from rencontres where NumGagnant IS NOT NULL " +
                    "order by NumGagnant asc");

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

}
