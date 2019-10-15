package controleurs;

import classesMetier.Equipes;
import coucheAccesBD.ExceptionAccesBD;
import coucheAccesBD.TablesDAO;
import coucheAccesBD.FabDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListerTables extends BaseFenetre
{
    @FXML private TableView<Equipes> TVEquipes;
    @FXML private Button BFermer;

    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerTables(Stage fenParent)
    {
        super(fenParent, "ListerTables.fxml", "Lister les tables", 635, 740);

        // ajouter la liste des cours à la table TVCours

//        try
//        {
//            TVTables.itemsProperty().setValue(FXCollections.observableArrayList(
//                    FabDAO.getInstance().getTablesDAO().listerTous()));
//        }
//        catch(ExceptionAccesBD e)
//        {
//            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
//            return;
//
//        }
//        if(TVEquipes.getItems().size() == 0)
//        {
//            new MsgBox(this, AlertType.INFORMATION, "Information",
//                    "Il n'y a pas d'arbitre dans la base de données!");
//            return;
//        }

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