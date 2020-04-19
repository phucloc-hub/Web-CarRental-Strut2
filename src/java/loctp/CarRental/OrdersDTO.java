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
public class OrdersDTO implements Serializable{
    private String email;
    private String carID;
    private String rentalDate;
    private String returnDate;
    private int quantity;
    private float price;
    private float total;
    private String codeDis;
    private String status;
    private String orderDate;

    public OrdersDTO() {
    }

    public OrdersDTO(String email, String carID, String rentalDate, String returnDate, int quantity, float price, float total, String codeDis, String status, String orderDate) {
        this.email = email;
        this.carID = carID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.codeDis = codeDis;
        this.status = status;
        this.orderDate = orderDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCodeDis() {
        return codeDis;
    }

    public void setCodeDis(String codeDis) {
        this.codeDis = codeDis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
