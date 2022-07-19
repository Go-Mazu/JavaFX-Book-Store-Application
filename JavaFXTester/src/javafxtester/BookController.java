/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author Govinda
 */
public class BookController implements Initializable{
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> BookNameColumn;
    @FXML
    private TableColumn<Book, Double> BookPriceColumn;
    
    
    

    public ObservableList<Book> getBook() throws FileNotFoundException{
       ObservableList<Book> book = FXCollections.observableArrayList();
        Scanner scanner = new Scanner(new File("BookNames.txt"));
        Scanner scanner2 = new Scanner(new File("BookPrices.txt"));
        while (scanner.hasNextLine()&& scanner2.hasNextDouble()) {
           String line = scanner.nextLine();
           double line2 = scanner2.nextDouble();
           book.add(new Book(line, line2));        
    }
        return book;

}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        BookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));
        try {
            tableView.setItems(getBook());
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    @FXML
    private TextField BookNameTextField;
    @FXML
    private TextField BookPriceTextField;
    
    public void newBookButtonPushed() throws FileNotFoundException{
        Book book = new Book(BookNameTextField.getText(),  Double.parseDouble(BookPriceTextField.getText()));
        tableView.getItems().add(book);
        BookNameTextField.clear();
        BookPriceTextField.clear();
        ObservableList<Book> allBook;
        allBook = tableView.getItems();     
    }
    

    public void deleteButtonPushed(){
        ObservableList<Book> selectedRows, allBooks;
        allBooks = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for(Book book: selectedRows){
            allBooks.remove(book);
        }
        for(Book book2: allBooks){
            File file = new File("BookNames.txt");
            try (FileWriter fl3 = new FileWriter(file)){
                fl3.write(book2.getBookName()+ System.getProperty( "line.separator" ));
                fl3.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            File file2 = new File("BookPrices.txt");
            try (FileWriter fl4 = new FileWriter(file2)){
                fl4.write(book2.getBookPrice()+ System.getProperty( "line.separator" ));
                fl4.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            }
        

    }
    public void bookBackButtonPushed() throws IOException{    
        Main m = new Main();
        m.changeScene("ManagerLogin.fxml");
        ObservableList<Book> allBooks;
        allBooks = tableView.getItems();

        List <List<String>> arrList = new ArrayList<>();
        List <List<Double>> arrList2 = new ArrayList<>();
        for(int i = 0; i < allBooks.size(); i++){

            arrList.add(new ArrayList<>());
            arrList.get(i).add(tableView.getItems().get(i).getBookName());
            arrList2.add(new ArrayList<>());
            arrList2.get(i).add(tableView.getItems().get(i).getBookPrice());            
        }
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j < arrList.get(i).size(); j++){
                System.out.println(arrList.get(i).get(j));
                Path output = Paths.get("BookNames.txt");
                Path output2 = Paths.get("BookPrices.txt");
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
}
