package controleurs;
import classesMetier.Rencontres;
import coucheAccesBD.*;
import java.net.URL;

import coucheMetier.CoucheMetier;
import coucheMetier.ExceptionMetier;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FenetrePrincipale extends Application
{
    Stage Fenetre;
    /**
     * Méthode exécutée au chargement. Elle charge la vue et affiche la fenêtre principale
     * @param fenetre : l'objet Stage représentant la fenêtre principale
     */
    @Override
    public void start(Stage fenetre)
    {
        Fenetre = fenetre;
// charger la vue dans la scene et associer l'instance courante en tant que contrôleur à
// cette vue
        try
        {
            FXMLLoader loader = new FXMLLoader(new URL("file:vues/FenetrePrincipale.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 750, 400, Color.WHITE);
            //scene.getStylesheets().add("vues/stylesCSS.css");
            fenetre.setScene(scene);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
// paramétrer et afficher la fenêtre principale
        fenetre.setTitle("Tournois");

        fenetre.setResizable(false);
        fenetre.centerOnScreen();
        fenetre.show();
    }
    /**
     * Méthode exécutée au démarrage de l'application
     * Elle crée la connexion à la base de données, puis elle charge la fenêtre principale
     */
    public static void main(String[] args)
    {
        try
        {
            FabDAO.getInstance().creerConnexion();
        }
        catch(ExceptionAccesBD e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        launch(args);
    }
    /**
     * Méthodes exécutées lorsque l'utilisateur clique sur les rubriques des menus
     */
    @FXML
    private void MListerArbitres(ActionEvent event)
    {
        new ListerArbitres(Fenetre);
    }

    @FXML
    private void MListerJoueurs(ActionEvent event)
    {
        new ListerJoueurs(Fenetre);
    }

    @FXML
    private void MSupprimerJoueur(ActionEvent event)
    {
        new SupprimerJoueur(Fenetre);
    }

    @FXML
    private void MModifierRencontres(ActionEvent event) { new ModifierRencontre(Fenetre); }

    @FXML
    private void MListerRencontres(ActionEvent event) { new ListerRencontres(Fenetre); }

    @FXML
    private void MListerRencontresEquipe(ActionEvent event) { new ListerRencontresEquipe(Fenetre); }

    @FXML
    private void MListerEquipes(ActionEvent event) { new ListerEquipes(Fenetre); }

    @FXML
    private void MAjouterRencontres(ActionEvent event)
    {
        try {
            CoucheMetier.getInstance().GenererRencontre();
        }

        catch (ExceptionMetier e)
        {
            new MsgBox(Fenetre, AlertType.WARNING, "Erreur lors de la génération de la rencontre", e.getMessage());
            return;

        }
    }

    @FXML
    private void MAjouterEquipe(ActionEvent event)
    {
        new AjouterEquipe(Fenetre);
    }

    @FXML
    private void MQuitter(ActionEvent event)
    {
        System.exit(0);
    }

}
