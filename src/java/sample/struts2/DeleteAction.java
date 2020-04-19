/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;
import java.util.Map;
import loctp.CarRental.OrdersBeen;
import loctp.cart.Cart;

/**
 *
 * @author Loc
 */
public class DeleteAction {

    private String carID;
    private final String SUCCESS = "success";

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public DeleteAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        Map sess = ActionContext.getContext().getSession();
        Cart shoppingCart = (Cart) sess.get("CART");
        if (shoppingCart != null) {
            shoppingCart.removefromCart(carID);
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
