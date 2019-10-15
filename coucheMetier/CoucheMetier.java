package coucheMetier;

import classesMetier.Arbitres;
import classesMetier.Equipes;
import classesMetier.Rencontres;
import classesMetier.Tables;
import coucheAccesBD.ExceptionAccesBD;
import coucheMetier.ExceptionMetier;
import coucheAccesBD.RencontresDAO;
import coucheAccesBD.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoucheMetier {

    private static CoucheMetier instance = new CoucheMetier();
    /**
     * Le constructeur est privé pour ne pas permettre à la classe ...
     * ... d'être instancée de l'extérieur
     */
    private CoucheMetier()
    {
    }
    /**
     * Méthode qui retourne l'unique instance de la fabrique
     * @return l'instance de la couche métier
     */
    public static CoucheMetier getInstance()
    {
        return instance;
    }

    /**
     * méthode qui va générer le tournois
     *
     */

    // Génération du tournois

    public void GenererRencontre() throws ExceptionMetier {

        try
        {
            int NbrRandom;
            String p;

            Random R = new Random();
            Rencontres rencontres = new Rencontres();

            List<Rencontres> rencontres1 = null;
            List<Equipes> equipes =  new ArrayList<Equipes>();
            List<Tables> tables = new ArrayList<Tables>();
            List<Arbitres> arbitres = new ArrayList<Arbitres>();

            if (rencontres1.size() == 0)
                p = "Quart";

            else if (rencontres1.size() == 4)
                p = "Demi finale";

            else
                p = "Finale";

            while (equipes.size() > 0)
            {
                rencontres.setPhase(p);

                NbrRandom = R.nextInt(tables.size());
                rencontres.setNumTable(tables.get(NbrRandom).getIdt());
                tables.remove(NbrRandom);

                NbrRandom = R.nextInt(arbitres.size());
                rencontres.setNumArbitre(arbitres.get(NbrRandom).getIda());
                arbitres.remove(NbrRandom);

                NbrRandom = R.nextInt(equipes.size());
                rencontres.setNumEquipe1(equipes.size());
                equipes.remove(NbrRandom);

                NbrRandom = R.nextInt(equipes.size());
                rencontres.setNumEquipe2(equipes.size());
                equipes.remove(NbrRandom);

                FabDAO.getInstance().getRencontresDAO().ajouter(rencontres);
            }
        }
        catch (Exception e)
        {
            throw new ExceptionMetier("Erreur lors de la génération du tournois");
        }
    }

}
