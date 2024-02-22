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
	Font errorFont = new Font("Times New Roman",11);
	User user = new User();
	
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		Pane p1 = new Pane();
		Scene t = new Scene(p1,350,240);
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
		
		//username and password
		Label usrNameLbl = new Label("Set your username:");
		usrNameLbl.setFont(txtFont);
		usrNameLbl.setTranslateX(3);
		usrNameLbl.setTranslateY(97);
		
		TextField usrTxtF = new TextField();
		usrTxtF.setTranslateX(165);
		usrTxtF.setTranslateY(95);
		
		Label passWrdLbl = new Label("Set your password:");
		passWrdLbl.setFont(txtFont);
		passWrdLbl.setTranslateX(3);
		passWrdLbl.setTranslateY(128);
		
		TextField passTxtF = new TextField();
		passTxtF.setTranslateX(165);
		passTxtF.setTranslateY(126);
		
		//ERROR MESSAGE
		Text errorMsg = new Text("Please enter a vaild name and pin number "
				+ "\nUsername must have 6 characters and Password must have 8 characters \nPassword must include speacial character:!@#$%&*");
		errorMsg.setX(3);
		errorMsg.setY(170);
		errorMsg.setFont(errorFont);
		errorMsg.setVisible(false);
		
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(305);
		nxtBtn.setTranslateY(210);
		
		nxtBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(nameTxtF.getText().matches("[a-zA-Z\s]*") && pinTxtF.getText().matches("[0-9]{4}")
						&& usrTxtF.getText().matches("[\\w]{6,}") && passTxtF.getText().matches("[\\w]{7,}"+"[!@#$%&*]+")) {
					user.setName(nameTxtF.getText());
					user.setPin(Integer.parseInt(pinTxtF.getText()));
					user.setUsername(usrTxtF.getText());
					user.setPassword(passTxtF.getText());
					t.setRoot(login(t));
				}else {
					errorMsg.setVisible(true);
				}
			}
		});
		
		Pane setupPane = new Pane();
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		setupPane.setBackground(background);
		setupPane.getChildren().addAll(title,nameLbl,nameTxtF,pinLabel,pinTxtF,usrNameLbl,usrTxtF,passWrdLbl,passTxtF,errorMsg,nxtBtn);
		return setupPane;
	}
	
	public Pane login(Scene t) {
		t.getWindow().setHeight(185);
		//Login Label
		Label logLbl = new Label("Please Login");
		logLbl.setFont(titleFont);
		logLbl.setTranslateX(3);
		
		//Enter username and password
		Label usrlogLbl = new Label("Enter username:");
		usrlogLbl.setFont(txtFont);
		usrlogLbl.setTranslateX(3);
		usrlogLbl.setTranslateY(33);
		
		TextField usrlogTxtF = new TextField();
		usrlogTxtF.setTranslateX(140);
		usrlogTxtF.setTranslateY(32);
		
		Label passlogLbl = new Label("Enter password:");
		passlogLbl.setFont(txtFont);
		passlogLbl.setTranslateX(3);
		passlogLbl.setTranslateY(66);
		
		TextField passlogTxtF = new TextField();
		passlogTxtF.setTranslateX(140);
		passlogTxtF.setTranslateY(64);
		
		//error message
		Text errorMsg = new Text("Incorrect username or password");
		errorMsg.setFont(errorFont);
		errorMsg.setX(3);
		errorMsg.setY(105);
		errorMsg.setVisible(false);
		
		//next button
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(305);
		nxtBtn.setTranslateY(120);
		
		nxtBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String inputUsrName = usrlogTxtF.getText();
				String inputPassWrd = passlogTxtF.getText();
				
				if (user.getUsername().matches(inputUsrName) && user.getPassword().matches(inputPassWrd)) {
					t.setRoot(ATM(t));
				}else {
					errorMsg.setVisible(true);
				}
			}
		});
		
		Pane loginPane = new Pane();
		loginPane.getChildren().addAll(logLbl,usrlogLbl,usrlogTxtF,passlogLbl,passlogTxtF,errorMsg,nxtBtn);
		return loginPane;
	}
	
	public Pane ATM(Scene t) {
		
		Pane atmPane = new Pane();
		return atmPane;
	}

}
