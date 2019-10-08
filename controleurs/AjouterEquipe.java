package controleurs;
import classesMetier.*;
import coucheAccesBD.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.*;
import java.io.File;
import java.nio.file.Files;


public class AjouterEquipe extends BaseFenetre{


    @FXML private TextField TFNom;
    @FXML private ComboBox<Joueurs> CBJoueur1;
    @FXML private ComboBox<Joueurs> CBJoueur2;


    public AjouterEquipe(Stage fenParent)
    {
        super(fenParent, "AjouterEquipe.fxml", "Ajouter une nouvelle équipe", 580, 260);

        try
        {
            CBJoueur1.setItems(FXCollections.observableArrayList(FabDAO.getInstance().getJoueursDAO().listerTous()));

            CBJoueur2.setItems(FXCollections.observableArrayList(FabDAO.getInstance().getJoueursDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
            return;
        }


        // afficher la fenêtre
        showAndWait();
    }

/**
 * méthode qui est exécutée quand on clique sur le bouton Ajouter et qui ajoute l'équipe dans la BD
 */

@FXML private void BAjouterEquipe()
{
    Equipes equipes = new Equipes();

    equipes.setNom(TFNom.getText());
    equipes.setJoueur1(CBJoueur1.getSelectionModel().getSelectedItem().getIdj());
    equipes.setJoueur2(CBJoueur2.getSelectionModel().getSelectedItem().getIdj());

    try
    {
        FabDAO.getInstance().getEquipesDAO().ajouter(equipes);
    }
    catch(ExceptionAccesBD e)
    {
        new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
    }
    catch (Exception e)
    {
        new MsgBox(this, AlertType.ERROR, "Erreur", e.getMessage());
        return;
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
