/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Govinda
 */
public class CheckoutWithPointsController extends CheckoutController implements Initializable {
    @FXML
    private Label TotalCost;
    @FXML
    private Label CustomerPoints;
    @FXML
    private Label CustomerStatus;  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String line;
        double sum = 0;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("PurchaseBookPrices.txt"));
            while ((line = reader.readLine()) != null) {
                sum += Double.parseDouble(line);
            }           
        } catch (Exception e) {
        } 
        System.out.println(sum);
        //TotalCost.setText(String.valueOf(sum));
        
        Scanner scanner4;
        try {
            scanner4 = new Scanner(new File("Points.txt"));
            while (scanner4.hasNextLine()) {
                int points = scanner4.nextInt();
                if(sum < (points/100)){
                    TotalCost.setText(String.valueOf(0));
                    points = (int) (points - (sum*100));
                    CustomerPoints.setText(String.valueOf(points));
                }
                else if (sum - (points/100)>0){
                    sum = sum - (points/100);
                    points = (int) (sum *10);
                    TotalCost.setText(String.valueOf(sum));
                    CustomerPoints.setText(String.valueOf(points));                    
                    if(points < 0){
                        points = 0;
                        CustomerPoints.setText(String.valueOf(points)); 
                    }
                }
                CustomerPoints.setText(String.valueOf(points));
//                TotalCost.setText(String.valueOf(sum));
                updateStatus(points);
                Path output = Paths.get("Points.txt");
                try {
                    Files.write(output, String.valueOf(points).getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void updateStatus(int points){
        if(points < 1000){
           CustomerStatus.setText("Silver"); 
        }
        else if(points >= 1000){
            CustomerStatus.setText("Gold");
        }    
    }
    
    @FXML
    void userlogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("FXML.fxml");
    }

    
}
