package application;
	
import java.util.ArrayList;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {

	Stage window;

	Button login, balanceinq, withdraw, deposit, prev, next, close;
	Scene scene1, scene2, scene3, withd, depst, bal, scene4, scene5;
	String pin1 = "0000",pass; 
	double balance = 0;
	String get,get2;
	int flag=0;
	double amount;
	String [] trans = new String[5];
	int count;
	Label trans1 = new Label();
	Label trans2 = new Label();
	Label inq = new Label();
	Label newb = new Label();
	TextField wit = new TextField();
	TextField depositTextField = new TextField();
	TextField withdrawTextField = new TextField();
	Label label2 = new Label();
	Label history = new Label();
	
    ArrayList<String> array= new ArrayList<String>();
    
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ATM");
		window = primaryStage;

		window.setOnCloseRequest(e -> close());
		Label label = new Label("Login");

		TextField pin = new TextField();
		
		Label menu = new Label("Main Menu \n");
		login = new Button("Submit");
		login.setOnAction(e -> {
			pass = pin.getText();
			if(IsNum(pin)){ 				
				if (pass.contentEquals(pin1)) {
					System.out.println("yes");
					window.setScene(scene2);
					
				}
				else {
					System.out.println("No");
					Alert.display("title", "Incorrect Pin");
				}
			}
		});
		
		
		balanceinq = new Button("Balance Inquiry");
		balanceinq.setOnAction(e -> balanceinquiry(balance));

		withdraw = new Button("Withdraw");

		withdraw.setOnAction(e -> {
			
		withdraw(withdrawTextField, withdrawTextField.getText());
		inq.setText("Your new balance is:"+ balance);
		
		});
		

		deposit = new Button("Deposit");
			
		deposit.setOnAction(e -> {
			
			balance+= deposit(depositTextField, depositTextField.getText());
			inq.setText("Your new balance is:"+balance);
		});

		prev = new Button("Previous");
		prev.setOnAction(e -> prevtransactions() );

		next = new Button("Next");
		next.setOnAction(e -> nexttrans());
		
		close = new Button("Close");
		close.setOnAction(e -> close());

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(label, pin, login);
		Scene scene1 = new Scene(layout, 300, 300);

		VBox lay2 = new VBox();
		lay2.getChildren().addAll(menu, balanceinq, withdraw, deposit, prev, next,inq,history);
		scene2 = new Scene(lay2, 600, 600);

		window.setScene(scene1);
		window.show();

	}

	public void prevtransactions() {
		if(count==0)
			history.setText("No more trans");
		else
		history.setText(array.get(--count));
		
	}
	
	public void nexttrans() {
		
		if(count==array.size()-1)
			history.setText("No more trans");
		else
		   history.setText(array.get(++count));
		
	}
	
	public double withdraw(TextField input, String string) {
		
		Stage withdrawwindow=new Stage();
		withdrawwindow.initModality(Modality.APPLICATION_MODAL);
		
		Label labell = new Label("Enter Amount");
		Label label2= new Label();
		TextField textt = new TextField();
		Button done1 = new Button("Done");
		done1.setOnAction(e -> {
			get2 = textt.getText();
			
				if(IsNum(textt)) {
					if (Double.parseDouble(get2) <= balance) {
						balance-=Double.parseDouble(get2);	
						withdrawwindow.close();
						array.add("Withdrawal= $" + textt.getText());
						count++;
					}
					else if (Double.parseDouble(get2)>balance)
					{ 
						System.out.println("Amount exceeded");
						label2.setText("Amount exceeded ur balance\nEnter a valid amount...");
						
					}
				flag=1;
				}
			else {
				System.out.println("Error");
		       	}			
		});
		VBox layout1 = new VBox();
		layout1.getChildren().addAll(done1,labell,textt,label2);
		Scene scenee = new Scene(layout1,300,300);
		withdrawwindow.setScene(scenee);
		withdrawwindow.showAndWait();
		
		
		if(flag==1)
			return balance;
		else
			return 0.0;
		
	}
		
		
	public double deposit(TextField amountt, String string) {
		
		Stage depositWindow = new Stage();
		depositWindow.initModality(Modality.APPLICATION_MODAL);
		
		Label label = new Label("Enter Amount");
		TextField text = new TextField();
		Button done = new Button("Done");
		done.setOnAction(e->{
			get = text.getText();
			if(IsNum(text)) {
				depositWindow.close();
				flag=1;
			}
			else {
				System.out.println("Error");
			}
		});
		VBox layout = new VBox();
		layout.getChildren().addAll(done,label,text);
		Scene scene = new Scene(layout,200,100);
		depositWindow.setScene(scene);
		depositWindow.showAndWait();
		array.add("Deposit = $" + text.getText());
		count++;
		
		if(flag==1)
			return Double.valueOf(get);
		else
			return 0.0;
		
	}

	private void balanceinquiry(double balance) {
			
			System.out.println("Your Current Balance is: $ " + balance);
			inq.setText("Your current balance is = $" + Double.toString(balance));


	}

	private static boolean IsNum(TextField input) {
		try {
			String gets= input.getText();
			double pin = Double.valueOf(gets);

			if(pin>=0) {
				return true;
			}
			else
				return false;
		} catch (NumberFormatException e) {
			System.out.println("Numbers only");

			return false;
		}
	}

	public void close() {
		System.out.println("Closing..");
		window.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}

}

