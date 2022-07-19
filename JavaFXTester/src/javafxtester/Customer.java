/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

/**
 *
 * @author Govinda
 */
public class Customer extends Manager{
    private String username;
    private String password;
    private int points;
    

    public Customer(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }  
}
