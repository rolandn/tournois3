package controleurs;
import classesMetier.*;
import coucheMetier.CoucheMetier;
import coucheMetier.CoucheMetier.*;
import classesMetier.Equipes;
import coucheAccesBD.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import coucheMetier.ExceptionMetier;
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
    @FXML private Button BModifierRencontreChoisie;

    @FXML private ComboBox<Rencontres> CBIdr;
    @FXML private TextField TFPhase;
    @FXML private TextField TFNumEquipe1;
    @FXML private TextField TFNumEquipe2;
    @FXML private TextField TFNumGagnant;
    @FXML private TextField TFScore;
    


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
                    "Il n'y a aucune rencontre dans la base de données!");
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
     * Méthode qui met à jour le contenu du contenu dans la fenêtre lors du changement
     * de la rencontre courant dans la boîte combo
     * @param rencontres : un objet Rencontres contenant les informations
     */

    private void CBChangerRencontre(Rencontres rencontres) {

        TFPhase.setText(rencontres.getPhase());
        TFNumEquipe1.setText(Integer.toString(rencontres.getNumEquipe1()));
        TFNumEquipe2.setText(Integer.toString(rencontres.getNumEquipe2()));
        TFNumGagnant.setText(Integer.toString(rencontres.getNumGagnant()));
        TFScore.setText(rencontres.getScore());
    }

    /**
     * méthode qui est exécutée quand on clique sur le bouton Modifier
     * et qui applique les modifications renseignés dans les champs.
     */
    @FXML
    public void BModifierRencontreChoisie()
    {
       // Rencontres rencontres = new Rencontres();
        int var = Integer.parseInt(TFNumGagnant.getText());
        Rencontres rencontres = new Rencontres(CBIdr.getSelectionModel().getSelectedItem());
        rencontres.setNumGagnant((Integer.parseInt(TFNumGagnant.getText())));

        try {
            CoucheMetier.getInstance().testerContrainteGagnant(rencontres);
            rencontres.setScore(TFScore.getText());
            //
            // rencontres.setIdr(CBIdr.getSelectionModel().getSelectedItem().getIdr());

        } catch (ExceptionMetier exceptionMetier) {
            new MsgBox(this, AlertType.INFORMATION, "ERREUR",
                    "Ce n'est pas une des deux équipes qui jouaient !");
            return;

        }

       // rencontres.setIdr(CBIdr.getSelectionModel().getSelectedItem().getIdr());
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




// ANCIEN CODE

// rencontres = new Rencontres();  // à enlever quand on aura fait le try/catch (si contrainte métier).
// --> il le faut car cela me retourne aussi les différents valeurs champs sinon
// tout est à 0 et j'aiune erreur SQL !

// vérifier les données sur l'élève

//       try
//        {
//            TFNumGagnant.getText(Integer.toString(rencontres.setNumGagnant()));
//
//        }
//       catch (ExceptionAccesBD e)
//        {
//            new MsgBox(this, AlertType.WARNING, "Erreur de test", e.getMessage());
//            return;
//        }

// !!!!!!!!!!!!!!!! faire l'inversse que pour l'affichage get --> set et set --> get



//  TFNumGagnant.getText(Integer.toString(rencontres.setNumGagnant()));
//  TFScore.getText(rencontres.setScore());