/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.cart;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import loctp.CarRental.OrdersBeen;

/**
 *
 * @author Loc
 */
public class Cart implements Serializable {

    private HashMap<String, OrdersBeen> items;

    // param 1: ID san pham
    // param 2 : DTO san pham
    public HashMap<String, OrdersBeen> getItems() {
        return items;
    }

    public void setItems(HashMap<String, OrdersBeen> items) {
        this.items = items;
    }

    public Cart() {
        items = new HashMap<>();
    }

    public void AddtoCart(OrdersBeen been) {
        if (items == null) {
            items = new HashMap<>();

        }
        try {
            String productID = been.getCarID();

            if (this.items.containsKey(been.getCarID()) && this.items.containsValue(been)) {
                String dateF = been.getDateF();
                String DateTo = been.getDateTo();
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                Date da1 = Date.valueOf(dateF);
                Date da2 = Date.valueOf(DateTo);
                c1.setTime(da1);
                c2.setTime(da2);
                long So_Ngay = (c2.getTime().getTime() - c1.getTime().getTime())/(24 * 3600 * 1000);

                int quantity = this.items.get(productID).getQuantity() + 1;
                this.items.get(productID).setQuantity(quantity);
                this.items.get(productID).setTotal(quantity * been.getPrice() * So_Ngay);
            }
            
            else if(!this.items.containsKey(been.getCarID())) {
                this.items.put(productID, been);
            }
            
        } catch (Exception ex) {
            System.out.println("Exception_AddtoCart: " + ex.getMessage());
        }

    }

    public void removefromCart(String productID) {
        if (this.items.containsKey(productID)) {
            this.items.remove(productID);
        }
        // check if items empty? -> set it null
        if (this.items.isEmpty()) {
            this.items = null;
        }

    }

    public void updateQuantity(String productID, int quantity) {
        if (this.items.containsKey(productID)) {
            //SET QUANTITY
            this.items.get(productID).setQuantity(quantity);
            // SET TOTAL
            String dateF = this.items.get(productID).getDateF();
            String DateTo = this.items.get(productID).getDateTo();
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            Date da1 = Date.valueOf(dateF);
            Date da2 = Date.valueOf(DateTo);
            c1.setTime(da1);
            c2.setTime(da2);
            long So_Ngay = (c2.getTime().getTime() - c1.getTime().getTime())/(24 * 3600 * 1000);

            this.items.get(productID).setTotal(quantity * this.items.get(productID).getPrice()*So_Ngay);
        }
        //IF QUANTITY =0 -> REMOVE IT
        if (this.items.get(productID).getQuantity() == 0) {
            this.items.remove(productID);
        }

    }

    public void clear() {
        this.items.clear();
    }

}
