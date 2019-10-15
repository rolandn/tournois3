package controleurs;

import classesMetier.Rencontres;
import coucheAccesBD.ExceptionAccesBD;
import coucheAccesBD.FabDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListerRencontres extends BaseFenetre
{
    @FXML private TableView<Rencontres> TVRencontres;
    @FXML private Button BFermer;

    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerRencontres(Stage fenParent)
    {
        super(fenParent, "ListerRencontres.fxml", "Lister les rencontres", 635, 740);

        // ajouter la liste des rencontres à la table TVRencontres

        try
        {
            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabDAO.getInstance().getRencontresDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;

        }
        if(TVRencontres.getItems().size() == 0)
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