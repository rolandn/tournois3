package controleurs;
import classesMetier.*;
import coucheAccesBD.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;
public class ListerArbitres extends BaseFenetre
{
    @FXML private TableView<Arbitres> TVArbitre;
    @FXML private Button BFermer;

    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerArbitres(Stage fenParent)
    {
        super(fenParent, "ListerArbitres.fxml", "Lister les arbitres", 635, 740);

        // ajouter la liste des cours à la table TVCours

        try
        {
            TVArbitre.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabDAO.getInstance().getArbitreDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;

        }
        if(TVArbitre.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a pas d'arbitre dans la base de données!");
            return;
        }





        // afficher la fenêtre

        showAndWait();
    }

    /**
     * méthode qui est exécutée quand on clique sur le bouton Fermer et qui ferme la fenêtre
     */

    @FXML
    private void BFermer(ActionEvent event)
    {
        close();
    }
}