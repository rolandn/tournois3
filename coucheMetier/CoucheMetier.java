package coucheMetier;
import controleurs.*;
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
            String q;
            String d;
            String f;

            Random R = new Random();


            List<Rencontres> rencontres1 = new ArrayList<Rencontres>();
            List<Rencontres> rencontres2 = FabDAO.getInstance().getRencontresDAO().listerTous();
            List<Equipes> equipes =  FabDAO.getInstance().getEquipesDAO().listerTous();
            List<Tables> tables = FabDAO.getInstance().getTablesDAO().listerTous();
            List<Arbitres> arbitres = FabDAO.getInstance().getArbitreDAO().listerTous();

            if (rencontres2.size() == 7) {
                FabDAO.getInstance().getRencontresDAO().toutSupprimer();
            }

            else {
                if (rencontres2.size() < 4) {

                    List<Rencontres> debut = FabDAO.getInstance().getRencontresDAO().listerTous();
                    q = "Quart";

                    while (debut.size() > 0 && rencontres2.size() < 5) {
                        Rencontres rencontres = new Rencontres();
                        rencontres.setPhase(q);

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

                        // ajouter transaction pour mettre 4 ou 0 rencontres.

                    }
                }

                else if (rencontres2.size() == 4)
                {
                    d = "Demi finale";
                    List<Rencontres> gagants_quart = FabDAO.getInstance().getEquipesQuartDAO().listerTous();

                    while (gagants_quart.size() > 0) {
                        Rencontres rencontres = new Rencontres();
                        rencontres.setPhase(d);

                        NbrRandom = R.nextInt(tables.size());
                        rencontres.setNumTable(tables.get(NbrRandom).getIdt());
                        tables.remove(NbrRandom);

                        NbrRandom = R.nextInt(arbitres.size());
                        rencontres.setNumArbitre(arbitres.get(NbrRandom).getIda());
                        arbitres.remove(NbrRandom);

                        NbrRandom = R.nextInt(gagants_quart.size());
                        rencontres.setNumEquipe1(gagants_quart.get(NbrRandom).getNumGagnant());
                        gagants_quart.remove(NbrRandom);

                        NbrRandom = R.nextInt(gagants_quart.size());
                        rencontres.setNumEquipe2(gagants_quart.get(NbrRandom).getNumGagnant());
                        gagants_quart.remove(NbrRandom);

                        FabDAO.getInstance().getRencontresDAO().ajouter(rencontres);

                        // ajouter transaction pour mettre 4 ou 0 rencontres.

                    }
                }

                else {
                    f = "Finale";
                    List<Rencontres> gagants_demi = FabDAO.getInstance().getEquipesDemiDAO().listerTous();

                    while (gagants_demi.size() > 0) {
                        Rencontres rencontres = new Rencontres();
                        rencontres.setPhase(f);

                        NbrRandom = R.nextInt(tables.size());
                        rencontres.setNumTable(tables.get(NbrRandom).getIdt());
                        tables.remove(NbrRandom);

                        NbrRandom = R.nextInt(arbitres.size());
                        rencontres.setNumArbitre(arbitres.get(NbrRandom).getIda());
                        arbitres.remove(NbrRandom);

                        NbrRandom = R.nextInt(gagants_demi.size());
                        rencontres.setNumEquipe1(gagants_demi.get(NbrRandom).getNumGagnant());
                        gagants_demi.remove(NbrRandom);

                        NbrRandom = R.nextInt(gagants_demi.size());
                        rencontres.setNumEquipe2(gagants_demi.get(NbrRandom).getNumGagnant());
                        gagants_demi.remove(NbrRandom);

                        FabDAO.getInstance().getRencontresDAO().ajouter(rencontres);

                        // ajouter transaction pour mettre 4 ou 0 rencontres.

                    }
                }
            }


        }
        catch (Exception e)
        {
            throw new ExceptionMetier("Erreur lors de la génération du tournois");
        }
    }

    /**
     * méthode qui teste si un n° d'équipe gagnant est valide
     * @param  : int = id équipe gagante
     * @return un résultat validé
     */
    public void testerContrainteGagnant(Rencontres rencontres) throws ExceptionMetier
    {


        if (rencontres.getNumEquipe1()!= rencontres.getNumGagnant())
            if(rencontres.getNumEquipe2()!= rencontres.getNumGagnant())
                       throw new ExceptionMetier("ERREUR " +
                               "Ce n'est pas une des deux équipes qui jouaient ensemble !");



    }

    public void testerContrainteEquipe (int ide) throws ExceptionMetier
    {
        try {
            List<Rencontres> list = FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(ide);

            if(list != null)
                throw new ExceptionMetier("ERREUR " +
                        "Cet équipe est impliquée dans le tournois !");
            else
                FabDAO.getInstance().getEquipesDAO().supprimer(ide);
        }
        catch (ExceptionAccesBD exceptionAccesBD) {
            exceptionAccesBD.printStackTrace();
        }
    }
    public void testerContrainteJoueur (int idj) throws ExceptionMetier
    {
        try {
            List<Equipes> list = FabDAO.getInstance().getEquipesDAO().chercherEquipe(idj);

            if(list.size() != 0)
                throw new ExceptionMetier("ERREUR " +
                        "Ce joueur appartient déjà à une équipe !");
            else
                FabDAO.getInstance().getJoueursDAO().supprimer(idj);

        }
        catch (ExceptionAccesBD exceptionAccesBD) {
            exceptionAccesBD.printStackTrace();
        }
    }

}
