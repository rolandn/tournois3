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

public class ModifierRencontre extends BaseFenetre
{


    @FXML private Button BFermer;

    @FXML private ComboBox<Rencontres> CBIdr;
    @FXML private TextField TFPhase;
    @FXML private TextField TFNumEquipe1;
    @FXML private TextField TFNumEquipe2;
    @FXML private TextField TFNumGagnant;
    @FXML private TextField TFScore;
    
    @FXML private Button BAjouter;

//    @FXML private ComboBox<Rencontres> CBRencontre;

    public ModifierRencontre(Stage fenParent)
    {
        super(fenParent, "ModifierRencontre.fxml", "Ajouter le résultat d'une rencontre", 1000, 600);

// ajouter les rencontres dans la boîte combo CBRencontre
        try
        {
            CBIdr.setItems(FXCollections.observableArrayList(
                    FabDAO.getInstance().getRencontresDAO().listerTous()));
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur de lecture dans la base de données",
                    e.getMessage());
            return;
        }
        if(CBIdr.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION, "Information",
                    "Il n'y a aucun élève dans la base de données!");
            return;
        }

        CBIdr.getSelectionModel().selectFirst();
        CBChangerRencontre(CBIdr.getItems().get(0));

        // gérer le changement de la rencontre courante dans la boîte combo CBRencontres

        CBIdr.getSelectionModel().selectedItemProperty().addListener((obs, ancRencontre, nouvRencontre) ->
        {
            if (nouvRencontre != null) CBChangerRencontre(nouvRencontre);
        });

        // afficher la fenêtre

        showAndWait();
    }

    /**
     * Méthode qui met à jour le contenu des contrôles dans la fenêtre lors du changement ...
     * ... de la rencontre courant dans la boîte combo
     * @param rencontres : un objet Rencontres contenant les informations
     */

    private void CBChangerRencontre(Rencontres rencontres) {

        TFPhase.setText(rencontres.getPhase());
        TFNumEquipe1.setText(Integer.toString(rencontres.getNumEquipe1()));
        TFNumEquipe2.setText(Integer.toString(rencontres.getNumEquipe2()));
        TFNumGagnant.setText(Integer.toString(rencontres.getNumGagnant()));
        TFScore.setText(rencontres.getScore());

    }


    @FXML
    private void BModifierRencontre()
    {
        Rencontres rencontres;
        rencontres = new Rencontres();  // à enlever quand on aura fait le try/catch (si contrainte métier).
        // --> il le faut car cela me retourne aussi les différents valeurs champs sinon
        // tout est à 0 et j'aiune erreur SQL !

        // vérifier les données sur l'élève

//        try
//        {
//            rencontres = coucheMetier.getInstance().TesterContraintesRencontre(TFNom.getText(),
//                    TFPrenom.getText(),
//                    TFPoids.getText(),
//                    CBAnnee.getValue(),
//                    TFDateNaissance.getText(),
//                    TFNomImage.getText());
//        }
//        catch (ExceptionMetier e)
//        {
//            new MsgBox(this, AlertType.WARNING, "Erreur de test", e.getMessage());
//            return;
//        }

        rencontres.setIdr(CBIdr.getSelectionModel().getSelectedItem().getIdr());


        try
        {
            FabDAO.getInstance().debuterTransaction();
            FabDAO.getInstance().getRencontresDAO().modifier(rencontres);
        }
        catch(ExceptionAccesBD e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur d'accès à la base de données", e.getMessage());
        }
        catch(Exception e)
        {
            new MsgBox(this, AlertType.ERROR, "Erreur", e.getMessage());
        }

        // annuler la modification dans la BD si un problème d'accès à la BD ou
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
