package coucheAccesBD;
import classesMetier.Equipes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FabDAO

{
    private static FabDAO Instance = new FabDAO(); // variable stockant l'unique instance de la fabrique

    private Connection SqlConn = null; // variable stockant la connexion à la base de données
    /**
     * Le constructeur est privé pour ne pas permettre à la classe FabDAO ...
     * ... d'être instancée de l'extérieur
     */
    private FabDAO()
    {
    }
    /**
     * Méthode qui retourne l'unique instance de la fabrique
     * @return l'instance de la couche métier
     */
    public static FabDAO getInstance()
    {
        return Instance;
    }
    /**
     * méthode qui crée une connexion avec la base de données
     */
    public void creerConnexion() throws ExceptionAccesBD
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            SqlConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "database=TOURNOIS;" +
                    "user=genial;" +
                    "password=super;");
        }
        catch(Exception e)
        {
            throw new ExceptionAccesBD(e.getMessage());
        }
    }
    /**
     * méthode qui débute une transaction
     */
    public void debuterTransaction() throws ExceptionAccesBD
    {
        try
        {
            SqlConn.setAutoCommit(false);
        }
        catch (SQLException e)

        {
            throw new ExceptionAccesBD(
                    e.getMessage());
        }
    }
    /**
     * méthode qui valide une transaction
     */
    public void validerTransaction() throws ExceptionAccesBD {
        try {
            SqlConn.commit();
            SqlConn.setAutoCommit(true);
        }
        catch (SQLException
                e)
        {
            throw new ExceptionAccesBD(
                    e.getMessage());
        }
    }
    /**
     * méthode qui annule une transaction
     */
    public void annulerTransaction() throws ExceptionAccesBD {
        try {
            SqlConn.rollback();
            SqlConn.setAutoCommit(true);
        }
        catch (SQLException
                e)
        {
            throw new ExceptionAccesBD(
                    e.getMessage());
        }
    }
    /**
     * méthode qui fournit une instance d'ArbitreDAO
     * @return l'instance d'ArbitreDAO
     */
    public ArbitreDAO getArbitreDAO() {
    return new ArbitreDAO(SqlConn);
}


    /**
     * méthode qui fournit une instance d'EquipeDAO
     * @return l'instance d'EquipeDAO
     */
    public EquipesDAO getEquipesDAO() {
        return new EquipesDAO(SqlConn);
    }

    /**
     * méthode qui fournit une instance d'EquipeQuartDAO
     * @return l'instance d'EquipeQuartDAO --> pour lister les équipes en quart de finale
     */
    public GagnantsQuartDAO getEquipesQuartDAO() {
        return new GagnantsQuartDAO(SqlConn);
    }

    /**
     * méthode qui fournit une instance d'EquipeDemiDAO
     * @return l'instance d'EquipeDemiDAO --> pour lister les équipes en quart de finale
     */
    public GagnantsDemiDAO getEquipesDemiDAO() {
        return new GagnantsDemiDAO(SqlConn);
    }


    /**
     * méthode qui fournit une instance d'ArbitreDAO
     * @return l'instance d'EleveDAO
     */
    public JoueursDAO getJoueursDAO() {
        return new JoueursDAO(SqlConn);
    }


    /**
     * méthode qui fournit une instance de TableDAO
     * @return l'instance TableDAO
     */
    public TablesDAO getTablesDAO() {
        return new TablesDAO(SqlConn);
    }

    /**
     * méthode qui fournit une instance de RencontresDAO
     * @return l'instance RencontresDAO
     */
    public RencontresDAO getRencontresDAO() {return new RencontresDAO(SqlConn); }

}


