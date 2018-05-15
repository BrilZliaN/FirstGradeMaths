package me.BrilZliaN.FirstGradeMaths;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui {

    @FXML
    private ResourceBundle resources;

	@FXML
    private URL location;

    @FXML
    private Button check;

    @FXML
    private Label output;

    @FXML
    private TextField input;

    @FXML
    private Label statTotal;

    @FXML
    private Label statRight;

    @FXML
    private Label statWrong;

    @FXML
    private Label statMark;

    @FXML
    private AnchorPane right;

    @FXML
    private AnchorPane wrong;
    
    private Stage stage;
    private Scene scene;
    private Expression current;
    private String mark;

    @FXML
    void initialize() {
        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'maths.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'maths.fxml'.";
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'maths.fxml'.";
        assert statTotal != null : "fx:id=\"statTotal\" was not injected: check your FXML file 'maths.fxml'.";
        assert statRight != null : "fx:id=\"statRight\" was not injected: check your FXML file 'maths.fxml'.";
        assert statWrong != null : "fx:id=\"statWrong\" was not injected: check your FXML file 'maths.fxml'.";
        assert statMark != null : "fx:id=\"statMark\" was not injected: check your FXML file 'maths.fxml'.";
        assert right != null : "fx:id=\"right\" was not injected: check your FXML file 'maths.fxml'.";
        assert wrong != null : "fx:id=\"wrong\" was not injected: check your FXML file 'maths.fxml'.";
        
        current = Expression.generate();
        
        output.setText(current.getExpression());
        
        check.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int answer = Integer.parseInt(input.getText());
					statTotal.setText((Integer.parseInt(statTotal.getText()) + 1) + "");
					if (answer == current.getAnswer()) {
						statRight.setText((Integer.parseInt(statRight.getText()) + 1) + "");
						animate(right);
					} else {
						statWrong.setText((Integer.parseInt(statWrong.getText()) + 1) + "");
						animate(wrong);
					}
					System.out.println(current.getExpression());
					System.out.println("Ответ: " + answer + "; Правильный ответ: " + current.getAnswer());
					calculateMark();
					input.setText("");
			        current = Expression.generate();
					input.setDisable(true);
					check.setDisable(true);
					System.out.println("---");
				} catch (Exception e) {
					e.printStackTrace();
					input.setText("");
					input.requestFocus();
				}
			}

		});
        
        input.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                	check.fire();
                } else {
                	checkSecretKey(ke.getCode());
                }
            }
        });
        
		Thread th = new Thread() {
			public void run() {
				try {
					sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Thread() {
					public void run() {
						input.requestFocus();
					}
				});
			}
		};
		th.start();
    }
    
    void animate(final AnchorPane pane) {
    	pane.setVisible(true);
		FadeTransition ft = new FadeTransition(Duration.millis(350), pane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		
		Thread t = new Thread() {
			public void run() {
				try {
					sleep(1400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Thread() {
					public void run() {
						FadeTransition ft = new FadeTransition(Duration.millis(750), pane);
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.play();
				        output.setText(current.getExpression());
					}
				});
				try {
					sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Thread() {
					public void run() {
				    	pane.setVisible(false);
						check.setDisable(false);
						input.setDisable(false);
						input.requestFocus();
					}
				});
			}
		};
		t.start();
    }
    
    void calculateMark() {
    	if (Integer.parseInt(statTotal.getText()) >= 5) {
    		double k = Integer.parseInt(statRight.getText()) * 1D / Integer.parseInt(statTotal.getText());
			System.out.println("Процент верных ответов: " + ((int)(k*100)) + "%");
    		int mark = (int) Math.ceil(k * 5.1);
    		if (mark < 2) mark = 2;
    		if (mark > 5) mark = 5;
			System.out.println("Оценка: " + mark + " (" + statRight.getText() + " : " + statWrong.getText() + ")");
    		if (Integer.parseInt(statWrong.getText()) == 0 && Integer.parseInt(statTotal.getText()) > 15) {
    			statMark.setText("5+");
    		} else {
    			statMark.setText(mark + "");
    		}
    	}
    }
    
    public void setStage(Stage stage) {
    	this.stage = stage;
    }
    
    public void setScene(Scene scene) {
    	this.scene = scene;
    }
    
    void checkSecretKey(KeyCode key) {
    	switch (key) {
    	case ESCAPE:
    	case F11:
    		stage.setFullScreen(!stage.isFullScreen());
    		Scale scale = stage.isFullScreen() ? new Scale(1.6, 1.36) : new Scale(1, 1);
    		scale.setPivotX(0);
    		scale.setPivotY(0);
    		scene.getRoot().getTransforms().setAll(scale);
    		break;
		default:
			break;
    	}
    }
}
