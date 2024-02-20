package banking;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ATM_FX extends Application{
	Font titleFont = new Font("Stencil",25);
	Font txtFont = new Font("Lucida Bright",17);
	User user = new User();
	
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		Pane p1 = new Pane();
		Scene t = new Scene(p1,350,140);
		t.setFill(Color.BLUE);
		t.setRoot(setup(t));
		mainStage.setScene(t);
		mainStage.show();
		mainStage.setTitle("ATM");
	}
	
	public Pane setup(Scene t) {
		
		//Title
		Label title = new Label("Create an Account");
		title.setFont(titleFont);
		title.setTranslateX(3);
		
		//Name Section
		Label nameLbl = new Label("Enter your full name:");
		nameLbl.setFont(txtFont);
		nameLbl.setTranslateX(3);
		nameLbl.setTranslateY(35);
		
		TextField nameTxtF = new TextField();
		nameTxtF.setTranslateX(180);
		nameTxtF.setTranslateY(33);
		
		//Pin label and text field
		Label pinLabel = new Label("Set your pin(4-Digit Code):");
		pinLabel.setFont(txtFont);
		pinLabel.setTranslateX(3);
		pinLabel.setTranslateY(66);
		
		TextField pinTxtF = new TextField();
		pinTxtF.setTranslateX(225);
		pinTxtF.setTranslateY(63);
		pinTxtF.setPrefWidth(50);
		
		//ERROR MESSAGE
		Text errorMsg = new Text("Please enter a vaild name and pin number");
		errorMsg.setX(3);
		errorMsg.setY(110);
		errorMsg.setVisible(false);
		
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(305);
		nxtBtn.setTranslateY(110);
		
		nxtBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(nameTxtF.getText().matches("[a-zA-Z\s]*") && pinTxtF.getText().matches("[0-9]{4}")) {
					user.setName(nameTxtF.getText());
					user.setPin(Integer.parseInt(pinTxtF.getText()));
					t.setRoot(ATM(t));
				}else {
					errorMsg.setVisible(true);
				}
			}
		});
		
		Pane setupPane = new Pane();
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		setupPane.setBackground(background);
		setupPane.getChildren().addAll(title,nameLbl,nameTxtF,pinLabel,pinTxtF,errorMsg,nxtBtn);
		return setupPane;
	}
	
	public Pane ATM(Scene t) {
		
		Pane loginPane = new Pane();
		return loginPane;
	}

}
