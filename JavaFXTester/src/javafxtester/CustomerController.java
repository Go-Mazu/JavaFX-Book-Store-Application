/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Govinda
 */
public class CustomerController implements Initializable{
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> UsernameColumn;
    @FXML
    private TableColumn<Customer, String> PasswordColumn;
    @FXML
    private TableColumn<Customer, Integer> PointsColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        PointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));
        try {
            tableView.setItems(getCustomer());
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    public ObservableList<Customer> getCustomer() throws FileNotFoundException{
       ObservableList<Customer> customer = FXCollections.observableArrayList();
        Scanner scanner = new Scanner(new File("Usernames.txt"));
        Scanner scanner2 = new Scanner(new File("Passwords.txt"));
        Scanner scanner3 = new Scanner(new File("Points.txt"));
        while (scanner.hasNextLine() && scanner2.hasNextLine() && scanner3.hasNextInt()) {
           String line = scanner.nextLine();
           String line2 = scanner2.nextLine();
           int line3 = scanner3.nextInt();
           customer.add(new Customer(line, line2, line3));        
    }
        return customer;

}
    @FXML
    private TextField UsernameTextField;
    @FXML
    private TextField PasswordTextField;
    
    public void newCustomerButtonPushed() throws FileNotFoundException{
        Customer customer = new Customer(UsernameTextField.getText(),  PasswordTextField.getText(), 0);
        tableView.getItems().add(customer);
        UsernameTextField.clear();
        PasswordTextField.clear();
        ObservableList<Customer> allCustomer;
        allCustomer = tableView.getItems();  
    }  
    public void deleteButtonPushed(){
        ObservableList<Customer> selectedRows, allCustomers;
        allCustomers = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for(Customer c: selectedRows){
            allCustomers.remove(c);
        }
        for(Customer c2: allCustomers){
            File file = new File("Usernames.txt");
            try (FileWriter fl3 = new FileWriter(file)){
                fl3.write(c2.getUsername()+ System.getProperty( "line.separator" ));
                fl3.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            File file2 = new File("Passwords.txt");
            try (FileWriter fl4 = new FileWriter(file2)){
                fl4.write(c2.getPassword()+ System.getProperty( "line.separator" ));
                fl4.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            File file3 = new File("Points.txt");
            try (FileWriter fl5 = new FileWriter(file3)){
                fl5.write(c2.getPoints()+ System.getProperty( "line.separator" ));
                fl5.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }            
            }
        

    }
        
    public void customerBackButtonPushed() throws IOException{    
        Main m = new Main();
        m.changeScene("ManagerLogin.fxml");
        ObservableList<Customer> allCustomers;
        allCustomers = tableView.getItems();

        List <List<String>> arrList = new ArrayList<>();
        List <List<String>> arrList2 = new ArrayList<>();
        List <List<Integer>> arrList3 = new ArrayList<>();
        for(int i = 0; i < allCustomers.size(); i++){

            arrList.add(new ArrayList<>());
            arrList.get(i).add(tableView.getItems().get(i).getUsername());
            arrList2.add(new ArrayList<>());
            arrList2.get(i).add(tableView.getItems().get(i).getPassword());
            arrList3.add(new ArrayList<>());
            arrList3.get(i).add(tableView.getItems().get(i).getPoints());            
        }
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j < arrList.get(i).size(); j++){
                System.out.println(arrList.get(i).get(j));
                Path output = Paths.get("Usernames.txt");
                Path output2 = Paths.get("Passwords.txt");
                Path output3 = Paths.get("Points.txt");
                try {
                    //Files.write(output, arrList.toString().replaceAll("(^\\[|\\]$)", "").replaceAll("(^\\[|\\]$)", "").replace("]", "[").getBytes());
                    //Files.write(output, (arrList.get(i)));
                    //Files.write(output, (arrList.stream().map(k -> k.toString()).collect(Collectors.joining(","))).getBytes());
                    // this one most recent, prints without brackets commas in a row: Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("]","").getBytes());
                    // prints in columns but with brackets on each: Files.write(output, arrList.toString().replaceAll("^.|.$", "").replace(", ", "\n").getBytes());
                    //Files.write(output, arrList.toString().replaceAll("^.|.$", "").replace(", ", "\n").getBytes());
                    //Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("] ","\n").getBytes());
                    Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                    Files.write(output2, arrList2.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                    Files.write(output3, arrList3.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }        
}
