package controleurs;
import classesMetier.*;
import coucheAccesBD.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.*;
import java.io.File;
import java.nio.file.Files;


public class AjouterEquipe extends BaseFenetre{


    @FXML private TextField TFNom;
    @FXML private ComboBox<String> CBJoueur1;
    @FXML private ComboBox<String> CBJoueur2;


    public AjouterEquipe(Stage fenParent)
    {
        super(fenParent, "AjouterEquipe.fxml", "Ajouter une nouvelle équipe", 580, 260);
        
        // afficher la fenêtre
        showAndWait();
    }

/**
 * méthode qui est exécutée quand on clique sur le bouton Ajouter et qui ajoute l'équipe dans la BD
 */

@FXML
private void BAjouterEquipe()
{
    Equipes equipes = null;

    int Joueur1 = CBJoueur1.getSelectionModel().getSelectedItem().getJoueur1(); // juste mettre les bonnes variables
    int Joueur1 = CBJoueur1.getSelectionModel().getSelectedItem().getJoueur1();

    // ajouter l'équipe dans la BD

    try
    {
        FabDAO.getInstance().debuterTransaction();

        FabDAO.getInstance().getEquipesDAO().ajouter(equipes);

        close();
        return;
    }
    catch(ExceptionAccesBD e)
    {
        new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
    }
    catch(Exception e)
    {
        new MsgBox(this, AlertType.ERROR, "Erreur", e.getMessage());
    }
    // annuler l'ajout dans la BD si un problème d'accès à la BD ou
    // si un problème de copie du fichier est survenu

    try
    {
        FabDAO.getInstance().annulerTransaction();
    }
    catch(ExceptionAccesBD e)
    {
        new MsgBox(this, AlertType.ERROR, "Erreur de transaction", e.getMessage());
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
