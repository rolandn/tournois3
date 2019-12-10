package coucheAccesBD;
import controleurs.ListerRencontres;
import controleurs.ListerRencontresEquipe;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
public abstract class BaseDAO<T>
{
    protected Connection SqlConn;
    protected PreparedStatement SqlCmd;
    /**
     * Constructeur
     * @param sqlConn : la connexion à la base de données
     */
    public BaseDAO(Connection sqlConn)
    {
        SqlConn = sqlConn;
    }
    /**
     * méthodes dont le comportement doit être défini dans les sous-classes DAO
     */
    public T charger(int num) throws ExceptionAccesBD
    {
        return null;
    }

    public void ajouter(T obj) throws ExceptionAccesBD
    {
    }

    public void modifier(T obj) throws ExceptionAccesBD
    {
    }

    public void supprimer(int num) throws ExceptionAccesBD
    {
    }

    public List<T> listerTous() throws ExceptionAccesBD
    {
        return null;
    }

    public List<T> listerRencontresEquipe (int Ide) throws ExceptionAccesBD
    {
        return null;
    }
}
