package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import me.BrilZliaN.FirstGradeMaths.Gui;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/style_large.fxml"));
            Pane page = (Pane) loader.load();
            Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
