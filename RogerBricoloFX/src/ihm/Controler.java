package ihm;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Garage;
import model.Moto;
import model.Reparation;
import model.Societe;
import model.Voiture;


public class Controler {
	
	@FXML
    private TreeView<Object> treeView;
    @FXML
    private ListView<String> listView;
    
    public Controler() {
    	
    }
    
    @FXML
    private void initialize() {
    	setTreeView();
    }
    
    private void setTreeView() {

    	Societe societe = application.Main.getSociete();
    	TreeItem<Object> root = new TreeItem<Object>(societe);
    	 root.setExpanded(true);
    	 for(Garage garage :societe.getList_garage()) {
    		 TreeItem<Object> ti_garage = new TreeItem<Object>(garage);
    		 for(Reparation reparation:garage.get_List_Reparation()) {
    			 ti_garage.getChildren().add(new TreeItem<Object>(reparation));
    		 }
    		 root.getChildren().add(ti_garage);
    	 }
    	 treeView.setRoot(root);
    }
    
    @FXML
    private void ParentSelect() {
    	listView.getItems().clear();
    	if(treeView.getSelectionModel().getSelectedItem()!=null) {
    		switch (treeView.getSelectionModel().getSelectedItem().getValue().getClass().getName()) {
        	case "model.Societe":
        		Societe societe = (Societe) treeView.getSelectionModel().getSelectedItem().getValue();
        		listView.getItems().add("Nom : "+societe.getNom());
        		break;
        	case "model.Garage":
        		Garage garage = (Garage) treeView.getSelectionModel().getSelectedItem().getValue();
        		listView.getItems().add("Nom : "+garage.getNom());
        		listView.getItems().add("Adresse : "+garage.getAdresse());
        		break;
        	case "model.Moto":
        		Moto moto = (Moto) treeView.getSelectionModel().getSelectedItem().getValue();
        		listView.getItems().add("Motif : "+moto.getMotif());
        		listView.getItems().add("Date d'arrivé : "+moto.getDate_arrive());
        		listView.getItems().add("SD : "+moto.isSd());
        		break;
        	case "model.Voiture":
        		Voiture voiture = (Voiture) treeView.getSelectionModel().getSelectedItem().getValue();
        		listView.getItems().add("Motif : "+voiture.getMotif());
        		listView.getItems().add("Date d'arrivé : "+voiture.getDate_arrive());
        		listView.getItems().add("Date Controle : "+voiture.getControle_technique());
        		break;
    		default :
    			System.out.println(treeView.getSelectionModel().getSelectedItem().getValue().getClass().getName());
        	}
    	}
    	
    	
    }
}
