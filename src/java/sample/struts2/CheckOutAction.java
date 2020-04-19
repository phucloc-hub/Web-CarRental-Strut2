/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import loctp.CarRental.CarDAO;
import loctp.CarRental.DiscountsDTO;
import loctp.CarRental.OrdersBeen;
import loctp.CarRental.OrdersDAO;
import loctp.cart.Cart;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Loc
 */
public class CheckOutAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CheckOutAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        int quantity = 0;
        String ErrorName = "";
        boolean IsEnough = true;
        Map sess = ActionContext.getContext().getSession();
        Cart shoppingCart = (Cart) sess.get("CART");
        if (shoppingCart != null) {
            HashMap<String, OrdersBeen> items = shoppingCart.getItems();
            OrdersDAO DAO = new OrdersDAO();
            CarDAO carDao = new CarDAO();
            for (String key : items.keySet()) {
                quantity = items.get(key).getQuantity();
                int CurrentQuantity = carDao.getQuantity_Left(items.get(key).getDateF(), items.get(key).getDateTo(), items.get(key).getCarID());
                if (CurrentQuantity == -1) {
                    CurrentQuantity = carDao.getQuantity(items.get(key).getCarID());
                }
                if (CurrentQuantity < quantity) {
                    ErrorName = items.get(key).getCarName();
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("ERROR", ErrorName + " has " + CurrentQuantity + " left!");
                    result = FAIL;
                    IsEnough = false;
                    break;
                }

            }
            if (IsEnough == true) {
                DiscountsDTO dto = (DiscountsDTO) sess.get("CodeDTO");
                int percent = 0;
                String codeDis = null;
                if (dto != null) {
                    codeDis = dto.getCodeDis();
                    percent = dto.getPercents();
                }

                for (String key : items.keySet()) {
                    //String email, String carID, String rentalDate, String returnDate
                    //, int quantity, float price, float total, String codeDis, String status,String orderDate

                    String email = items.get(key).getEmail();
                    String carID = items.get(key).getCarID();
                    String rentalDate = items.get(key).getDateF();
                    String returnDate = items.get(key).getDateTo();
                    int quantity2 = items.get(key).getQuantity();
                    float price2 = items.get(key).getPrice();
                    float total = items.get(key).getTotal();

                    String status = "Active";

                    String orderDate = items.get(key).getOrderDate();

                    if (codeDis != null) {
                        float total2 = total - (total * percent / 100);
                        DAO.insertOrder(email, carID, rentalDate, returnDate, quantity2, price2, total2, codeDis, status, orderDate);
                    } else {
                        DAO.insertOrder(email, carID, rentalDate, returnDate, quantity2, price2, total, null, status, orderDate);
                    }

                }
                shoppingCart.clear();
                sess.remove("CART");
            }

        }
        return result;
    }
}
