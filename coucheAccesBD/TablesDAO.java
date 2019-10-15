package coucheAccesBD;
import java.sql.*;
import java.util.*;
import classesMetier.*;
import controleurs.*;

public class TablesDAO extends BaseDAO<Tables>
{
    public TablesDAO(Connection sqlConn)
    {
        super(sqlConn);
    }

    /**
     * méthode qui lit dans la base de données tous les cours
     * @return la liste de tous les cours
     */

    public List<Tables> listerTous() throws ExceptionAccesBD
    {
        ArrayList<Tables> liste = new ArrayList<Tables>();
        try
        {
            SqlCmd = SqlConn.prepareCall("select idt, nom, position " +
                    "from tables " +
                    "order by nom asc");

            ResultSet sqlRes = SqlCmd.executeQuery();

            while (sqlRes.next() == true)
                liste.add(new Tables(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getInt(3)));
            sqlRes.close();
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }

        return liste;
    }
}

