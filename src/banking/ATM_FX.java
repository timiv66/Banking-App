package banking;

import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
import javafx.util.converter.IntegerStringConverter;

public class ATM_FX extends Application{
	Font titleFont = new Font("Stencil",25);
	Font txtFont = new Font("Lucida Bright",17);
	Font errorFont = new Font("Times New Roman",11);
	Font btnFont = new Font("Elephant",18);
	Font finalFont = new Font("Georgia",12);
	DropShadow shadow = new DropShadow();
	Account account = new Account();
	User user = new User(account);
	
	
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
		t.getWindow().setHeight(240);
		
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
		depBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t.setRoot(deposit(t));
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
		chkbalBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(balance(t));
			}
			
		});
		
		//Settings Button
		Button setBtn = new Button("Settings");
		setBtn.setTranslateX(205);
		setBtn.setTranslateY(160);
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
		setBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(settings(t));
			}
		});
		
		//Logout Button
		Button logoutBtn = new Button("Logout");
		logoutBtn.setTranslateX(3);
		logoutBtn.setTranslateY(172);
		
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
		atmPane.getChildren().addAll(welcLbl,transLbl,line,withBtn,depBtn,chkbalBtn,setBtn,logoutBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		atmPane.setBackground(background);
		return atmPane;
	}
	
	public Pane withdraw(Scene t) {
		t.getWindow().setHeight(200);
		
		//only numbers accepted in txt fields
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String text = change.getText();
			// Check if the new text is a number or empty
			if (text.matches("[0-9]*") || text.isEmpty()) {
				return change;
			}
			// If not a number or empty, reject the change
			return null;
		};
		TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), null, filter);
		
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
		pinTxtF.setPrefWidth(50);
		pinTxtF.setTextFormatter(textFormatter1);
		
		//error message
		Text errorMsg = new Text("");
		errorMsg.setFont(errorFont);
		errorMsg.setX(2);
		errorMsg.setY(73);
		errorMsg.setVisible(false);
		
		//enter button
		Button enterBtn = new Button("Enter");
		enterBtn.setTranslateX(305);
		enterBtn.setTranslateY(133);
		
		//Final amount withdrawn and final balance amount
		Text finalTxt = new Text("");
		finalTxt.setX(3);
		finalTxt.setY(100);
		finalTxt.setVisible(false);
		finalTxt.setFont(finalFont);
		
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
		enterBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int inputPin = Integer.parseInt(pinTxtF.getText());
					account.setWithAmt(Double.parseDouble(withTxtF.getText()));
					if(inputPin != user.getPin()) {
						finalTxt.setVisible(false);
						errorMsg.setText("Invalid PIN");
						errorMsg.setVisible(true);
					}	
					else {
						if (account.getWithAmt() > account.getBalance()) {
							errorMsg.setText("Insufficient Funds! Get your money up");
							errorMsg.setVisible(true);
						}
						else if(account.getWithAmt() <= account.getBalance()) {
							errorMsg.setVisible(false);
							account.setBalance(account.getBalance() - account.getWithAmt());
							account.setLatestTrac(account.getWithAmt());
							finalTxt.setText("You have withdrawn $" +  String.format("%.2f",account.getWithAmt()) + ".\nYour current balance is $" + String.format("%.2f",account.getBalance()));
							finalTxt.setVisible(true);
						}
					}
				}catch(Exception e) {
					errorMsg.setText("Please input valid numbers only");
					errorMsg.setVisible(true);
				}
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
		
		Pane withPane = new Pane(withLbl,withTxtF,pinLbl,pinTxtF,errorMsg,enterBtn,backBtn,finalTxt);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		withPane.setBackground(background);
		return withPane;
	}
	
	public Pane deposit(Scene t) {
		t.getWindow().setHeight(200);
		
		//only numbers accepted in txt fields
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String text = change.getText();
			// Check if the new text is a number or empty
			if (text.matches("[0-9]*") || text.isEmpty()) {
				return change;
			}
			// If not a number or empty, reject the change
			return null;
		};
		TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), null, filter);
		
		//Withdraw label and text field
		Label depLbl = new Label("Amount to deposit:$");
		depLbl.setTranslateX(2);
		depLbl.setTranslateY(5);
		depLbl.setFont(txtFont);
		
		TextField depTxtF = new TextField();
		depTxtF.setTranslateX(175);
		depTxtF.setTranslateY(3);
		
		//Pin label and text field
		Label pinLbl = new Label("Enter PIN:");
		pinLbl.setTranslateX(2);
		pinLbl.setTranslateY(38);
		pinLbl.setFont(txtFont);
		
		TextField pinTxtF = new TextField();
		pinTxtF.setTranslateX(85);
		pinTxtF.setTranslateY(35);
		pinTxtF.setPrefWidth(50);
		pinTxtF.setTextFormatter(textFormatter1);
		
		//error message
		Text errorMsg = new Text("");
		errorMsg.setFont(errorFont);
		errorMsg.setX(2);
		errorMsg.setY(73);
		errorMsg.setVisible(false);
		
		//enter button
		Button enterBtn = new Button("Enter");
		enterBtn.setTranslateX(305);
		enterBtn.setTranslateY(133);
		
		//Final amount withdrawn and final balance amount
		Text finalTxt = new Text("");
		finalTxt.setX(3);
		finalTxt.setY(100);
		finalTxt.setVisible(false);
		finalTxt.setFont(finalFont);
		
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
		enterBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int inputPin = Integer.parseInt(pinTxtF.getText());
					account.setDepAmt(Double.parseDouble(depTxtF.getText()));
					if(inputPin != user.getPin()) {
						finalTxt.setVisible(false);
						errorMsg.setText("Invalid PIN");
						errorMsg.setVisible(true);
					}	
					else {
						errorMsg.setVisible(false);
						account.setBalance(account.getBalance() + account.getDepAmt());
						account.setLatestTrac(account.getDepAmt());
						finalTxt.setText("You have deposited $" +  String.format("%.2f",account.getDepAmt()) + "\n Your current balance is $" + String.format("%.2f",account.getBalance()));
						finalTxt.setVisible(true);
					}
				}catch(Exception e) {
					errorMsg.setText("Please input valid numbers only");
					errorMsg.setVisible(true);
				}
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
		
		Pane depPane = new Pane(depLbl,depTxtF,pinLbl,pinTxtF,errorMsg,enterBtn,backBtn,finalTxt);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		depPane.setBackground(background);
		return depPane;
	}
	
	public Pane balance(Scene t) {
		t.getWindow().setHeight(150);
		
		//Balance Amount Txt
		Text balTxt = new Text(account.getName() + " Balance: $"+String.format("%.2f", account.getBalance()));
		balTxt.setX(2);
		balTxt.setY(20);
		balTxt.setFont(txtFont);
		
		//Latest Transaction Txt
		Text tracTxt = new Text("Latest Transaction:");
		tracTxt.setX(2);
		tracTxt.setY(48);
		tracTxt.setFont(txtFont);
		
		if(account.getLatestTrac()== 0) {
			tracTxt.setText("Latest Transaction: N/A");
		}else if(account.getLatestTrac() == account.getDepAmt()) {
			tracTxt.setText("Latest Transaction: Deposited $" + String.format("%.2f",account.getLatestTrac()));
		}else if(account.getLatestTrac() == account.getWithAmt()) {
			tracTxt.setText("Latest Transaction: Withdrawn $" + String.format("%.2f",account.getLatestTrac()));
		}
		
		//back button
		Button backBtn = new Button("Back");
		backBtn.setTranslateX(3);
		backBtn.setTranslateY(80);
		
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
		
		
		Pane balPane = new Pane(balTxt,tracTxt,backBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		balPane.setBackground(background);
		return balPane;
	}
	
	public Pane settings(Scene t) {
		t.getWindow().setHeight(200);
		//Title
		Label setLbl = new Label("Settings");
		setLbl.setFont(titleFont);
		setLbl.setTranslateX(3);
		
		Line line = new Line();
		line.setStartX(0); 
		line.setEndX(400); 
		line.setStartY(30);
		line.setEndY(30);
		line.setSmooth(true);
		line.setStroke(Color.RED);
		line.setStrokeWidth(5);
		
		//Change username setting
		Button usrBtn = new Button("Username");
		usrBtn.setTranslateX(40);
		usrBtn.setTranslateY(35);
		usrBtn.setPrefSize(130, 30);
		usrBtn.setFont(btnFont);
		
		usrBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  usrBtn.setEffect(shadow);
	          }
	        });
		usrBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  usrBtn.setEffect(null);
	          }
	        });
		usrBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(changeUsr(t));
			}
		});
		
		//Change password setting
		Button passBtn = new Button("Password");
		passBtn.setTranslateX(180);
		passBtn.setTranslateY(35);
		passBtn.setPrefSize(130, 30);
		passBtn.setFont(btnFont);
		
		passBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  passBtn.setEffect(shadow);
	          }
	        });
		passBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  passBtn.setEffect(null);
	          }
	        });
		passBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		//Change pin setting
		Button pinBtn = new Button("PIN");
		pinBtn.setTranslateX(40);
		pinBtn.setTranslateY(75);
		pinBtn.setPrefSize(130, 30);
		pinBtn.setFont(btnFont);
		
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
		pinBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		//account settings
		Button accBtn = new Button("Account");
		accBtn.setTranslateX(180);
		accBtn.setTranslateY(75);
		accBtn.setPrefSize(130, 30);
		accBtn.setFont(btnFont);
		
		accBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  accBtn.setEffect(shadow);
	          }
	        });
		accBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  accBtn.setEffect(null);
	          }
	        });
		accBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		//Back Button
		Button backBtn = new Button("Back");
		backBtn.setTranslateX(307);
		backBtn.setTranslateY(132);
		
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
				// TODO Auto-generated method stub
				t.setRoot(ATM(t));
			}
		});
		
		Pane setPane = new Pane(setLbl,line,usrBtn,passBtn,pinBtn,accBtn,backBtn);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		setPane.setBackground(background);
		return setPane;
	}
	
	public Pane changeUsr (Scene t) {
		//Title
		Label titLbl = new Label("Change Username");
		titLbl.setFont(titleFont);
		titLbl.setTranslateX(3);
		
		Line line = new Line();
		line.setStartX(0); 
		line.setEndX(400); 
		line.setStartY(30);
		line.setEndY(30);
		line.setSmooth(true);
		line.setStroke(Color.RED);
		line.setStrokeWidth(5);
		
		//Current Username
		Label currUsrLbl = new Label("Current Username:");
		currUsrLbl.setFont(txtFont);
		currUsrLbl.setTranslateX(3);
		currUsrLbl.setTranslateY(39);
		
		TextField currUsrTxtF = new TextField();
		currUsrTxtF.setTranslateX(162);
		currUsrTxtF.setTranslateY(38);
		
		//New Username
		Label newUsrLbl = new Label("New Username:");
		newUsrLbl.setFont(txtFont);
		newUsrLbl.setTranslateX(3);
		newUsrLbl.setTranslateY(69);
		
		TextField newUsrTxtF = new TextField();
		newUsrTxtF.setTranslateX(132);
		newUsrTxtF.setTranslateY(68);
		
		//Error message
		Text errorMsg = new Text("");
		errorMsg.setFont(errorFont);
		errorMsg.setX(2);
		errorMsg.setY(113);
		errorMsg.setVisible(false);
		
		Text finalTxt = new Text("");
		finalTxt.setX(2);
		finalTxt.setY(113);
		finalTxt.setVisible(true);
		finalTxt.setFont(finalFont);
		
		//Back button
		Button backBtn = new Button("Back");
		backBtn.setTranslateX(3);
		backBtn.setTranslateY(132);
		
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
				// TODO Auto-generated method stub
				t.setRoot(settings(t));
			}
		});
		
		//Set new username
		Button enterBtn = new Button("Enter");
		enterBtn.setTranslateX(305);
		enterBtn.setTranslateY(132);
		
		enterBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  enterBtn.setEffect(shadow);
	          }
	        });
		enterBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	        	  backBtn.setEffect(null);
	          }
	        });
		enterBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(currUsrTxtF.getText().matches(user.getUsername()) && newUsrTxtF.getText().matches("[\\w]{6,}")) {
					user.setUsername(newUsrTxtF.getText());
					finalTxt.setVisible(true);
					errorMsg.setVisible(false);
					finalTxt.setText("New username has been set");
				}else if(!currUsrTxtF.getText().matches(user.getUsername())) {
					errorMsg.setVisible(true);
					finalTxt.setVisible(false);
					errorMsg.setText("Incorrect current username");
				}else if(!newUsrTxtF.getText().matches("[\\w]{6,}")) {
					errorMsg.setVisible(true);
					finalTxt.setVisible(false);
					errorMsg.setText("Username needs to be at least 6 characters");
				}
			}
		});
		
		
		Pane chgUsrPane = new Pane(titLbl,line,currUsrLbl,currUsrTxtF,newUsrLbl,newUsrTxtF,backBtn,enterBtn,errorMsg,finalTxt);
		BackgroundFill background_fill = new BackgroundFill(Color.PINK,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		chgUsrPane.setBackground(background);
		return chgUsrPane;
	}

}
