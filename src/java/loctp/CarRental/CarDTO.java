/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.CarRental;

import java.io.Serializable;

/**
 *
 * @author Loc
 */
public class CarDTO implements Serializable{
    private String carID;
    private String carName;
    private String color;
    private String year;
    private int categoryID;
    private float price;
    private int quantity;
    private String status;
    
    public CarDTO() {
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CarDTO(String carID, String carName, String color, String year, int categoryID, float price, int quantity, String status) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

   
   



  
    
}