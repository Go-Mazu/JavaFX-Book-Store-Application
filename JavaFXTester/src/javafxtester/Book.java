/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Govinda
 */
public class Book {
    private String BookName;
    private double BookPrice;

    public Book(String BookName, double BookPrice) {
        this.BookName = BookName;
        this.BookPrice = BookPrice;
    }

    public String getBookName() {
        return BookName;
    }

    public double getBookPrice() {
        return BookPrice;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public void setBookPrice(double BookPrice) {
        this.BookPrice = BookPrice;
    }
    
    
}
