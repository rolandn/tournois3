package controleurs;

import classesMetier.Joueurs;
import coucheAccesBD.ExceptionAccesBD;
import coucheAccesBD.FabDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListerJoueurs extends BaseFenetre
{
    @FXML private TableView<Joueurs> TVJoueurs;
    @FXML private Button BFermer;

    /**
     * Constructeur qui crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerJoueurs(Stage fenParent)
    {
        super(fenParent, "ListerJoueurs.fxml", "Lister les joueurs", 635, 740);

        // ajouter la liste des cours à la table TVCours

        try
        {
            TVJoueurs.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabDAO.getInstance().getJoueursDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;

        }
        if(TVJoueurs.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a pas de joueurs dans la base de données!");
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