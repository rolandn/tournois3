package controleurs;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;
public class MsgBox extends BaseFenetre
{
    @FXML private Label LMsgErreur;
    @FXML private ImageView IVImage;
    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     * @param typeErreur : la nature du message à afficher (information, attention ou erreur)
     * @param msgErreur : le message à afficher
     */
    public MsgBox(Stage fenParent, AlertType typeErreur, String msgTitre, String msgErreur)
    {
        super(fenParent, "MsgBoxVue.fxml", "", 1470, 1100);

// fixer l'image et le message dans la fenêtre
        if(typeErreur == AlertType.INFORMATION)
        {
            IVImage.setImage(new Image("file:imgs/imgsmsgbox/information.jpg"));
        }
        else if(typeErreur == AlertType.WARNING)
        {
            IVImage.setImage(new Image("file:imgs/imgsmsgbox/attention.jpg"));
        }
        else if(typeErreur == AlertType.ERROR)
        {
            IVImage.setImage(new Image("file:imgs/imgsmsgbox/erreur.jpg"));
        }

        setTitle(msgTitre);
        LMsgErreur.setText(msgErreur);

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

