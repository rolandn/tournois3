package controleurs;

import classesMetier.*;
import classesMetier.Rencontres;
import classesMetier.Equipes;
import coucheAccesBD.*;
import coucheAccesBD.ExceptionAccesBD;
import coucheAccesBD.FabDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ListerRencontresEquipe extends BaseFenetre
{
    @FXML private Button BFermer;
    @FXML private ComboBox<Equipes> CBIde;
    @FXML private TableView<Rencontres> TVRencontres;

    public ListerRencontreEquipe(Stage fenParent)  // touver pourquoi cette erreur?? mauvaise {{{ ?
    {
        super(fenParent, "ListerRencontresEquipe.fxml", "Lister les rencontres d'une équipe", 1000, 600);

        try
        {
            CBIde.setItems(FXCollections.observableArrayList(
                    FabDAO.getInstance().getEquipesDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur de lecture dans la base de données",
                    e.getMessage());
            return;
        }
        if(CBIde.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a aucune rencontre dans la base de données!");
            return;
        }

        CBIde.getSelectionModel().selectFirst();
        TVListerRencontre(CBIde.getItems().get(0).getJoueur1());

        // afficher la fenêtre

        showAndWait();
    }

    public void TVListerRencontre(int num)
    {
        try
        {
//            List<Rencontres> liste =
//                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(num);
//
//            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(liste));

            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(num)));
        }
        catch (ExceptionAccesBD e) {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;
        }

        if(TVRencontres.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a pas de joueurs dans la base de données!");
            return;
        }
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
