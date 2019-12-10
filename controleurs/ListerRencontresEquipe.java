package controleurs;

import classesMetier.*;
import classesMetier.Rencontres;
import classesMetier.Equipes;
import coucheAccesBD.*;
import coucheAccesBD.RencontresDAO;
import coucheAccesBD.EquipesDAO;
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

import java.util.List;

public class ListerRencontresEquipe extends BaseFenetre
{
    @FXML private Button BFermer;
    @FXML private ComboBox<Equipes> CBIde;
    @FXML private TableView<Rencontres> TVRencontres;

    public ListerRencontresEquipe(Stage fenParent)
    {
        super(fenParent, "ListerRencontresEquipe.fxml", "Lister les rencontres d'une équipe", 1500, 900);

        try
        {
            CBIde.setItems(FXCollections.observableArrayList(FabDAO.getInstance().getEquipesDAO().listerTous()));
        }
        catch(Exception e)
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
        CBChangerEquipe(CBIde.getItems().get(0).getIde());



        CBIde.getSelectionModel().selectedItemProperty().addListener((obs, ancEquipe, nouvEquipe) ->
        {
            if (nouvEquipe != null)
                CBChangerEquipe(nouvEquipe.getIde());
        });
        CBIde.getSelectionModel().selectFirst();
        CBChangerEquipe(CBIde.getItems().get(0).getIde());
        showAndWait();
    }

    private void CBChangerEquipe(int Ide) {

        try
        {
//            List<Rencontres> list =
//                    FabDAO.getInstance().getRencontresDAO().ListerRencontresEquipe(Ide);
//            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(list));


            List<Rencontres> liste =
                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(Ide);

            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(liste));

           TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(Ide)));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;
        }
    }

//    public void TVListerRencontre(Equipes num)
//    {
//        try
//        {
////            List<Rencontres> liste =
////                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(num);
////
////            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(liste));
//
//            TVRencontres.itemsProperty().setValue(FXCollections.observableArrayList(
//                    FabDAO.getInstance().getRencontresDAO().listerRencontresEquipe(num)));
//        }
//        catch (ExceptionAccesBD e) {
//            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
//            return;
//        }
//
//        if(TVRencontres.getItems().size() == 0)
//        {
//            new MsgBox(this, AlertType.INFORMATION, "Information",
//                    "Il n'y a pas de joueurs dans la base de données!");
//            return;
//        }
//    }

    /**
     * méthode qui est exécutée quand on clique sur le bouton Fermer et qui ferme la fenêtre
     */
    @FXML
    private void BFermer(ActionEvent event)
    {
        close();
    }
}
