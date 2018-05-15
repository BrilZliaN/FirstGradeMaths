package me.BrilZliaN.FirstGradeMaths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Loader extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/style_large.fxml"));
            Pane page = (Pane) loader.load();
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setTitle("Считай!");
            Gui gui = (Gui) loader.getController();
            gui.setScene(scene);
            gui.setStage(stage);
            stage.show();
            stage.setWidth(600);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.sizeToScene();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
