/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.CarRental;

import java.io.Serializable;

/**
 *
 * @author Loc OrdersDTO(email, carID,carName,DateF, DateTo, Quantity, price,
 * price*Quantity, codeDis, "Active",orderDate)
 */
public class OrdersBeen implements Serializable {

    private String email;
    private String carID;
    private String carName;
    private String dateF;
    private String dateTo;
    private int quantity;
    private float price;
    private float total;
    private String status;
    private String orderDate;
    private String category;

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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public OrdersBeen(String email, String carID, String carName, String dateF, String dateTo, int quantity, float price, float total, String status, String orderDate, String category) {
        this.email = email;
        this.carID = carID;
        this.carName = carName;
        this.dateF = dateF;
        this.dateTo = dateTo;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrdersBeen) {

            if (((OrdersBeen) obj).dateF.equals(this.dateF) &&((OrdersBeen) obj).dateTo.equals(this.dateTo) 
                    && ((OrdersBeen) obj).carID.equals(this.carID)
                    ) {

                return true;

            }
            

        }

        return false;

    }
//  ( Object A == Object B ) <=> equals return TRUE ,hashcode return the same code
    @Override
    public int hashCode() {
        return 1+ Integer.parseInt(this.getCarID());//
    }

    public OrdersBeen() {
    }

}
