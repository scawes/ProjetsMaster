package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Garage;
import model.Societe;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	static Societe societe;
	
	@Override
	public void start(Stage primaryStage) {
		//FXMLLoader loader = new FXMLLoader();
		// Path to the FXML File
		//String fxmlDocPath = "src/ihm/Accueil_IHM.fxml";
		//FileInputStream fxmlStream;
		try {
			//fxmlStream = new FileInputStream(fxmlDocPath);
			// Create the Pane and all Details
			//AnchorPane root = loader.load(fxmlStream);
			URL fichier = getClass().getResource("/ihm/Accueil_IHM.fxml");
			Parent root = FXMLLoader.load(fichier);
			
			// Create the Scene
			Scene scene = new Scene(root);
			// Set the Scene to the Stage
			primaryStage.setScene(scene);
			// Set the Title to the Stage
			primaryStage.setTitle("Gestion Garage");
			// Display the Stage
			primaryStage.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		societe = new Societe("Roger");
		Garage g = societe.add_garage("MonGarage", "Brest");//new Garage();
		g.add_voiture(new Date(), "test",new Date());
		g.add_moto(new Date(), "test",true);
		g.add_voiture(new Date(), "test2",new Date());
		launch(args);
	}
	
	public static Societe getSociete() {
		return societe;
		
	}
}
