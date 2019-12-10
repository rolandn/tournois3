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

public class SupprimerJoueur  extends BaseFenetre {

    @FXML private Button BFermer;
    @FXML private Button BSupprimer;
    @FXML private ComboBox<Joueurs> CBJoueurs;
    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public SupprimerJoueur(Stage fenParent)
    {
        super(fenParent, "SupprimerJoueurVue.fxml", "Supprimer un joueur", 320, 95);

// ajouter les élèves dans la boîte combo CBEleves
        try
        {
            CBJoueurs.setItems(FXCollections.observableArrayList(
                    FabDAO.getInstance().getJoueursDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;
        }
        if(CBJoueurs.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a aucun joueur dans la base de données!");
            return;
        }
        CBJoueurs.getSelectionModel().selectFirst();

        showAndWait();
    }
    /**
     * méthode exécutée quand on clique sur le bouton Supprimer et qui supprime ...
     * ... l'élève et les résultats obtenus dans la BD
     */
    @FXML
    private void BSupprimerJoueur()
    {
        int idj = CBJoueurs.getSelectionModel().getSelectedItem().getIdj();
        try
        {
            FabDAO.getInstance().debuterTransaction();
            FabDAO.getInstance().getEquipesDAO().supprimer(idj);
            FabDAO.getInstance().getJoueursDAO().supprimer(idj);
            FabDAO.getInstance().validerTransaction();
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
