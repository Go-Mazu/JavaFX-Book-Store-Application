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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SelectionController implements Initializable{
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableView<Book> Removed;    
    @FXML
    private TableColumn<Book, String> BookNameColumn;
    @FXML
    private TableColumn<Book, Double> BookPriceColumn;
    @FXML
    private TableColumn<Book, String> PurchasedNameColumn;
    @FXML
    private TableColumn<Book, Double> PurchasedPriceColumn;    
    @FXML
    private Label CustomerName;
    @FXML
    private Label CustomerPoints;
    @FXML
    private Label CustomerStatus;
    
    public ObservableList<Book> getBook() throws FileNotFoundException{
       ObservableList<Book> book = FXCollections.observableArrayList();
        Scanner scanner = new Scanner(new File("BookNames.txt"));
        Scanner scanner2 = new Scanner(new File("BookPrices.txt"));
        Scanner scanner3 = new Scanner(new File("Usernames.txt"));
        Scanner scanner4 = new Scanner(new File("Points.txt"));
        while (scanner.hasNextLine()&& scanner2.hasNextDouble()) {
           String line = scanner.nextLine();
           double line2 = scanner2.nextDouble();
           book.add(new Book(line, line2));        
    }
        while (scanner3.hasNextLine() && scanner4.hasNextLine()) {
            String name = scanner3.nextLine();
            CustomerName.setText(name);
            int points = scanner4.nextInt();
            CustomerPoints.setText(String.valueOf(points));
            updateStatus(points);
        }
        return book;
    }
    
    public void updateStatus(int points){
        if(points < 1000){
           CustomerStatus.setText("Silver"); 
        }
        else if(points >= 1000){
            CustomerStatus.setText("Gold");
        }    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookNameColumn.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        BookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("BookPrice"));

        try {
            tableView.setItems(getBook());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    } 
    
    @FXML
    void buyButtonPushed(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CustomerCheckoutScreen.fxml");
        ObservableList<Book> allBooks;
        allBooks = tableView.getItems();

        List <List<String>> arrList = new ArrayList<>();
        List <List<Double>> arrList2 = new ArrayList<>();
        for(int i = 0; i < allBooks.size(); i++){
            arrList.add(new ArrayList<>());
            arrList.get(i).add(allBooks.get(i).getBookName());
            arrList2.add(new ArrayList<>());
            arrList2.get(i).add(allBooks.get(i).getBookPrice()); 
        }        
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j < arrList.get(i).size(); j++){
                System.out.println(arrList.get(i).get(j));
                Path output = Paths.get("BookNames.txt");
                Path output2 = Paths.get("BookPrices.txt");
                try {
                    Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                    Files.write(output2, arrList2.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }   
    }
    
    @FXML
    void buywithpointsButtonPushed(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("CustomerCheckoutScreenwithPoints.fxml");
        ObservableList<Book> allBooks;
        allBooks = tableView.getItems();

        List <List<String>> arrList = new ArrayList<>();
        List <List<Double>> arrList2 = new ArrayList<>();
        for(int i = 0; i < allBooks.size(); i++){
            arrList.add(new ArrayList<>());
            arrList.get(i).add(allBooks.get(i).getBookName());
            arrList2.add(new ArrayList<>());
            arrList2.get(i).add(allBooks.get(i).getBookPrice()); 
        }        
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j < arrList.get(i).size(); j++){
                System.out.println(arrList.get(i).get(j));
                Path output = Paths.get("BookNames.txt");
                Path output2 = Paths.get("BookPrices.txt");
                try {
                    Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                    Files.write(output2, arrList2.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } 
    }
    
    @FXML
    void selectButtonPushed(ActionEvent event) {
        ObservableList<Book> selectedRows, allBooks, removedBooks;
        allBooks = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        removedBooks = Removed.getItems();
        for(Book book: selectedRows){
            allBooks.remove(book);
            removedBooks.add(book);
        }
        List <List<String>> arrList = new ArrayList<>();
        List <List<Double>> arrList2 = new ArrayList<>();
        for(int i = 0; i < removedBooks.size(); i++){
            arrList.add(new ArrayList<>());
            arrList.get(i).add(removedBooks.get(i).getBookName());
            arrList2.add(new ArrayList<>());
            arrList2.get(i).add(removedBooks.get(i).getBookPrice()); 
        }        
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j < arrList.get(i).size(); j++){
                System.out.println(arrList.get(i).get(j));
                Path output = Paths.get("PurchaseBookNames.txt");
                Path output2 = Paths.get("PurchaseBookPrices.txt");
                try {
                    Files.write(output, arrList.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                    Files.write(output2, arrList2.toString().replaceAll(",","").replace("[","").replace("[","").replace("] ","\n").replace("]","").getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    void userlogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("FXML.fxml");
    }
    
}
