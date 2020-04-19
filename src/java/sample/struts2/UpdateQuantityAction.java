/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import loctp.CarRental.OrdersBeen;
import loctp.cart.Cart;

/**
 *
 * @author Loc
 */
public class UpdateQuantityAction extends ActionSupport {

    private String quantity;
    private String carID;

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    private final String SUCCESS = "success";

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public UpdateQuantityAction() {
    }

    @Override
    public void validate() {
        if (Integer.parseInt(quantity) < 0) {
            addFieldError("quantity", "Positive number");
        }
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        Map sess = ActionContext.getContext().getSession();
        Cart shoppingCart = (Cart) sess.get("CART");
        int quantityInt = Integer.parseInt(quantity);
        if (shoppingCart != null) {
            shoppingCart.updateQuantity(carID, quantityInt);
            float total = 0;
            HashMap<String, OrdersBeen> items = shoppingCart.getItems();
            for (String CarID : items.keySet()) {
                total = total + items.get(CarID).getTotal();
            }
            sess.put("totalSeSS", total);
            sess.remove("totalss");
        }

        return result;
    }

}
