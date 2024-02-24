package banking;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class ATM_FX extends Application{
	Font titleFont = new Font("Stencil",25);
	Font txtFont = new Font("Lucida Bright",17);
	Font errorFont = new Font("Times New Roman",11);
	Font btnFont = new Font("Elephant",18);
	DropShadow shadow = new DropShadow();
	Account account = new Account();
	User user = new User();
	
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		Pane p1 = new Pane();
		Scene t = new Scene(p1,350,150);
		t.setFill(Color.BLUE);
		t.setRoot(login(t));
		mainStage.setScene(t);
		mainStage.show();
		mainStage.setTitle("ATM");
	}
	
	public Pane login(Scene t) {
		
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
		Button loginBtn = new Button("Login");
		loginBtn.setTranslateX(180);
		loginBtn.setTranslateY(120);
		
		loginBtn.setOnAction(new EventHandler <ActionEvent>() {
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
		
		//Sign Up
		Button signUpBtn = new Button("Sign Up");
		signUpBtn.setTranslateX(110);
		signUpBtn.setTranslateY(120);
		
		signUpBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(setup(t));
			}
		});
		
		Pane loginPane = new Pane();
		loginPane.getChildren().addAll(logLbl,usrlogLbl,usrlogTxtF,passlogLbl,passlogTxtF,errorMsg,loginBtn,signUpBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		loginPane.setBackground(background);
		return loginPane;
	}
	
	public Pane setup(Scene t) {
		t.getWindow().setHeight(280);
		
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
		
		Button backBtn = new Button("Back");
		backBtn.setTranslateX(3);
		backBtn.setTranslateY(210);
		
		backBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t.setRoot(login(t));
			}
			
		});
		
		Pane setupPane = new Pane();
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		setupPane.setBackground(background);
		setupPane.getChildren().addAll(title,nameLbl,nameTxtF,pinLabel,pinTxtF,usrNameLbl,usrTxtF,passWrdLbl,passTxtF,errorMsg,nxtBtn,backBtn);
		return setupPane;
	}
	
	public Pane ATM(Scene t) {
		t.getWindow().setHeight(280);
		
		//Welcome label
		Label welcLbl = new Label("Welcome, " + user.getName());
		welcLbl.setFont(titleFont);
		welcLbl.setTranslateX(3);
		
		//Transaction
		Label transLbl = new Label("Select your transaction:");
		transLbl.setFont(txtFont);
		transLbl.setTranslateX(3);
		transLbl.setTranslateY(39);
		
		Line line = new Line();
		line.setStartX(0); 
		line.setEndX(400); 
		line.setStartY(30);
		line.setEndY(30);
		line.setSmooth(true);
		line.setStroke(Color.RED);
		line.setStrokeWidth(5);
		
		//Withdraw option
		Button withBtn = new Button("Withdraw");
		withBtn.setTranslateX(205);
		withBtn.setTranslateY(35);
		withBtn.setPrefSize(130, 30);
		withBtn.setFont(btnFont);
		withBtn.setAlignment(Pos.CENTER_LEFT);
		
		withBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  withBtn.setEffect(shadow);
	          }
	        });
		withBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  withBtn.setEffect(null);
	          }
	        });
		withBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(withdraw(t));
			}
		});
		
		//Deposit option
		Button depBtn = new Button("Deposit");
		depBtn.setTranslateX(205);
		depBtn.setTranslateY(76);
		depBtn.setPrefSize(130, 30);
		depBtn.setFont(btnFont);
		depBtn.setAlignment(Pos.CENTER_LEFT);
		
		depBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  depBtn.setEffect(shadow);
	          }
	        });
		depBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  depBtn.setEffect(null);
	          }
	        });
		
		//Check Balance option
		Button chkbalBtn = new Button("Balance");
		chkbalBtn.setTranslateX(205);
		chkbalBtn.setTranslateY(117);
		chkbalBtn.setPrefSize(130, 30);
		chkbalBtn.setFont(btnFont);
		chkbalBtn.setAlignment(Pos.CENTER_LEFT);
		
		chkbalBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  chkbalBtn.setEffect(shadow);
	          }
	        });
		chkbalBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  chkbalBtn.setEffect(null);
	          }
	        });
		
		
		//Pin Button
		Button pinBtn = new Button("Change PIN");
		pinBtn.setTranslateX(205);
		pinBtn.setTranslateY(158);
		pinBtn.setPrefSize(140, 30);
		pinBtn.setFont(btnFont);
		pinBtn.setAlignment(Pos.CENTER_LEFT);
		
		pinBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  pinBtn.setEffect(shadow);
	          }
	        });
		pinBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  pinBtn.setEffect(null);
	          }
	        });
		
		//Settings Button
		Button setBtn = new Button("Settings");
		setBtn.setTranslateX(205);
		setBtn.setTranslateY(199);
		setBtn.setPrefSize(130, 30);
		setBtn.setFont(btnFont);
		setBtn.setAlignment(Pos.CENTER_LEFT);
		
		setBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  setBtn.setEffect(shadow);
	          }
	        });
		setBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  setBtn.setEffect(null);
	          }
	        });
		
		//Logout Button
		Button logoutBtn = new Button("Logout");
		logoutBtn.setTranslateX(3);
		logoutBtn.setTranslateY(210);
		
		logoutBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  logoutBtn.setEffect(shadow);
	          }
	        });
		logoutBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  logoutBtn.setEffect(null);
	          }
	        });
		logoutBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t.setRoot(login(t));
			}
		});
		
		Pane atmPane = new Pane();
		atmPane.getChildren().addAll(welcLbl,transLbl,line,withBtn,depBtn,chkbalBtn,pinBtn,setBtn,logoutBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		atmPane.setBackground(background);
		return atmPane;
	}
	
	public Pane withdraw(Scene t) {
		t.getWindow().setHeight(200);
		
		//Withdraw label and text field
		Label withLbl = new Label("Amount to withdrawl:$");
		withLbl.setTranslateX(2);
		withLbl.setTranslateY(5);
		withLbl.setFont(txtFont);
		
		TextField withTxtF = new TextField();
		withTxtF.setTranslateX(195);
		withTxtF.setTranslateY(3);
		
		//Pin label and text field
		Label pinLbl = new Label("Enter PIN:");
		pinLbl.setTranslateX(2);
		pinLbl.setTranslateY(38);
		pinLbl.setFont(txtFont);
		
		TextField pinTxtF = new TextField();
		pinTxtF.setTranslateX(85);
		pinTxtF.setTranslateY(35);
		
		//error message
		Text errorMsg = new Text("");
		errorMsg.setFont(errorFont);
		errorMsg.setX(2);
		errorMsg.setY(73);
		
		//enter button
		Button enterBtn = new Button("Enter");
		enterBtn.setTranslateX(305);
		enterBtn.setTranslateY(133);
		
		enterBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  enterBtn.setEffect(shadow);
	          }
	        });
		enterBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  enterBtn.setEffect(null);
	          }
	        });
		
		//back button
		Button backBtn = new Button("Back");
		backBtn.setTranslateX(3);
		backBtn.setTranslateY(133);
		
		backBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  backBtn.setEffect(shadow);
	          }
	        });
		backBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  backBtn.setEffect(null);
	          }
	        });
		backBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(ATM(t));
				
			}
			
		});
		
		
		
		Pane balancePane = new Pane(withLbl,withTxtF,pinLbl,pinTxtF,errorMsg,enterBtn,backBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		balancePane.setBackground(background);
		return balancePane;
	}

}
