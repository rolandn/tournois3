package controleurs;

import classesMetier.Equipes;
import coucheAccesBD.ExceptionAccesBD;
import coucheAccesBD.FabDAO;
import coucheMetier.CoucheMetier;
import coucheMetier.ExceptionMetier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SupprimerEquipe extends BaseFenetre {

    @FXML private Button BFermer;
    @FXML private Button BSupprimer;
    @FXML private ComboBox<Equipes> CBEquipe;
    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public SupprimerEquipe(Stage fenParent)
    {
        super(fenParent, "SupprimerEquipeVue.fxml", "Supprimer une équipe", 1320, 195);

// ajouter les équipe dans la boîte combo CBEquipe
        try
        {
            CBEquipe.setItems(FXCollections.observableArrayList(
                    FabDAO.getInstance().getEquipesDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;
        }
        if(CBEquipe.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a aucune équipe dans la base de données!");
            return;
        }
        CBEquipe.getSelectionModel().selectFirst();

        showAndWait();
    }
    /**
     * méthode exécutée quand on clique sur le bouton Supprimer et qui supprime ...
     * ... l'élève et les résultats obtenus dans la BD
     */
    @FXML
    private void BSupprimerEquipe()
    {
        int ide = CBEquipe.getSelectionModel().getSelectedItem().getIde();

            try {
                CoucheMetier.getInstance().testerContrainteEquipe(ide);
                FabDAO.getInstance().debuterTransaction();
              //  FabDAO.getInstance().getEquipesDAO().supprimer(ide);
                FabDAO.getInstance().validerTransaction();
            }
            catch (ExceptionMetier e) {
                try
                {
                    FabDAO.getInstance().annulerTransaction();
                }
                catch(ExceptionAccesBD ex)
                {
                }
                new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            }

            catch(ExceptionAccesBD e)
            {
                try
                {
                    FabDAO.getInstance().annulerTransaction();
                }
                catch(ExceptionAccesBD ex)
                {
                }
                new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            }
            close();

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
