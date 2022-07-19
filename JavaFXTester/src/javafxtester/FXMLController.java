package javafxtester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLController {
    
    @FXML
    public Button loginButton;
    @FXML
    public Label wrongLogin;
    @FXML
    public TextField username;
    @FXML
    public PasswordField pass;
    
    Stage newWindow;
    Customer customer;
    Manager manager = new Manager();
    ArrayList<Customer> newCustomers;

    public void userLogIn(ActionEvent event) throws IOException {
        loginButtonClicked();

    }
    @FXML
    public Label CustomerName;
    public void loginButtonClicked() throws IOException{
        Main main = new Main();

        if(username.getText().toString().equals(manager.getUsername()) && pass.getText().toString().equals(manager.getPassword())) {
            wrongLogin.setText("Success!");
            main.changeScene("ManagerLogin.fxml");
        }
        if(isUserValid(username.getText().toString())&& isPassValid(pass.getText().toString())) {
            wrongLogin.setText("Success!");
            main.changeScene("CustomerSelectionScreen.fxml");
        }

        else if(username.getText().isEmpty() && pass.getText().isEmpty()) {
            wrongLogin.setText("Please enter your credentials");
        }
        
        else {
            wrongLogin.setText("Incorrect username or password!");
        }
    }
    public boolean isUserValid(String username) throws FileNotFoundException, IOException{
    try (FileReader fr = new FileReader("Usernames.txt");
         BufferedReader r = new BufferedReader(fr)) {
         String read = r.readLine();
         while (read != null) {
             if (read.contains(username)) {
                 return true;
             }
             read = r.readLine();
         }
         return false; 
    }        
    }
    public boolean isPassValid(String password) throws FileNotFoundException, IOException{
    try (FileReader fr = new FileReader("Passwords.txt");
         BufferedReader r = new BufferedReader(fr)) {
         String read = r.readLine();
         while (read != null) {
             if (read.contains(password)) {
                 return true;
             }
             read = r.readLine();
         }
         return false; 
    }        
    }    
    
    @FXML
    public Button logout;
    public void userlogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("FXML.fxml");
    }
    @FXML
    public Button book;
    public void manageBooks(ActionEvent event) throws IOException {
        Main m = new Main();       
        m.changeScene("Managerbookscreen.fxml");
    }
    @FXML
    public Button Customers;
    public void manageCustomers(ActionEvent event) throws IOException {
        Main m = new Main();       
        m.changeScene("ManagerAddCustomer.fxml");
    }   

}

