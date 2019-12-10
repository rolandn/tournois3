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

public class AjouterJoueur extends BaseFenetre {

    @FXML private TextField TFNom;
    @FXML private TextField TFPrenom;
    @FXML private TextField TFStyle;
    @FXML private TextField TFNomImage;
    @FXML private Button BChargerImage;
    @FXML private Button BAjouter;
    @FXML private Button BFermer;
    @FXML private ImageView IVImage;
    private File FichierSrc = null;

/**
 * Constructeur qui crée la fenêtre
 * @param fenParent : l'objet Stage représentant la fenêtre parent
 */
public AjouterJoueur(Stage fenParent)
{
    super(fenParent, "AjouterJoueur.fxml", "Ajouter un joueur", 880, 360);
// afficher la fenêtre
    showAndWait();
}
    /**
     * méthode qui ouvre une boîte de dialogue permettant de sélectionner une image sur un disque
     */

    @FXML
    private void BOuvrirFichierImg()
    {
        FileChooser btNomImage = new FileChooser();
        btNomImage.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        FichierSrc = btNomImage.showOpenDialog(null);
        if(FichierSrc != null)
        {
            TFNomImage.setText(FichierSrc.getName());
            IVImage.setImage(new Image("file:" + FichierSrc.getPath()));
        }
    }
    /**
     * méthode qui est exécutée quand on clique sur le bouton Ajouter et qui ajoute l'élève dans la BD
     */
    @FXML
    private void BAjouterJoueur()
    {
        Joueurs joueurs = new Joueurs();

        joueurs.setNom(TFNom.getText());
        joueurs.setPrenom(TFPrenom.getText());
        joueurs.setStyle(TFStyle.getText());

        try
        {
            FabDAO.getInstance().getJoueursDAO().ajouter(joueurs);
        }
        catch (ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
        }

        // ajouter le joueur dans la BD et copier l'image

        try
        {
            FabDAO.getInstance().debuterTransaction();

            FabDAO.getInstance().getJoueursDAO().ajouter(joueurs);

            if (FichierSrc != null)
            {
                File FichierDst = new File(System.getProperty("user.dir") + "/imgs/imgseleves/" +
                        FichierSrc.getName());
                Files.copy(FichierSrc.toPath(), FichierDst.toPath(), REPLACE_EXISTING);
            }
            FabDAO.getInstance().validerTransaction();

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
