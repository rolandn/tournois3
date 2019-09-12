package controleurs;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;
public class BaseFenetre extends Stage
{
    /**
     * constructeur qui charge la vue courante, associe le contrôleur et paramètre la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     * @param vue : le nom du fichier XML stockant la vue
     * @param titre : le titre de la fenêtre
     * @param largeur : la largeur de la fenêtre
     * @param hauteur : la hauteur de la fenêtre
     */
    protected BaseFenetre(Stage fenParent, String vue, String titre, int largeur, int hauteur)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(new URL("file:vues/" + vue));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), largeur, hauteur);
           // scene.getStylesheets().add("vues/stylesCSS.css");
            setScene(scene);
        }
        catch(Exception e)
        {
            System.out.println("Problème : " + e.getMessage());
            System.exit(0);
        }
        setTitle(titre);
        setResizable(false);
        setX(fenParent.getX() + (fenParent.getWidth() - largeur) / 1);
        setY(fenParent.getY() + (fenParent.getHeight() - hauteur) / 1);
        initOwner(fenParent);
        initModality(Modality.APPLICATION_MODAL);
    }
}

