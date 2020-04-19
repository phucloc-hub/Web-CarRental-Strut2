/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import loctp.CarRental.CarDTO;
import loctp.CarRental.CategoryDAO;
import loctp.CarRental.OrdersBeen;
import loctp.cart.Cart;

/**
 *
 * @author Loc
 */
public class AddtoCartAction {

    private String carName;
    private String carID;
    private float price;

    private String DateF;
    private String DateTo;
    private int categoryID;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String DateTo) {
        this.DateTo = DateTo;
    }

    public String getDateF() {
        return DateF;
    }

    public void setDateF(String DateF) {
        this.DateF = DateF;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }
    private String color;

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

    public AddtoCartAction() {
    }
//    private static void getCarID()

    public String execute() throws Exception {
        //email,CarID,rentalDate,ReturnDate,Quantity,Price,Total,CodeDis,Status
        // email tu sess
        // CarID tu getListDTO (ket qua vua search duoc luu trong nay)

//       for(Car dto: listDTo){
//           dto.getName==carName -> carID= dto.getCarID
//                                  -> Price=dto.getPrice  
//           
//       }
        // Date lay tu trang home param.
        //Quantity = 1
        // Price lay o tren
        // Total = Price*Quantity
        // codeDis lay tu param
        //
        Map sess = ActionContext.getContext().getSession();

        String email = (String) sess.get("Email");
        ArrayList<CarDTO> list = (ArrayList<CarDTO>) sess.get("RESULT");
        for (CarDTO dto : list) {
            if (dto.getCarName().equals(carName)) {
                carID = dto.getCarID();
                price = dto.getPrice();
            }

        }
        CategoryDAO cateDAO = new CategoryDAO();
        String category = cateDAO.getCategory(categoryID);

        int Quantity = 1;
        Cart shoppingCart = (Cart) sess.get("CART");
        if (shoppingCart == null) {
            shoppingCart = new Cart();
        }
        String orderDate = LocalDate.now().toString();

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date da1 = Date.valueOf(DateF);
        Date da2 = Date.valueOf(DateTo);
        c1.setTime(da1);
        c2.setTime(da2);
        long So_Ngay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        //String email, String carID, String carName, String dateF, String dateTo,
        //int quantity, float price, float total, String status, String orderDate, String category
        // add to cart
        OrdersBeen been = new OrdersBeen(email, carID, carName, DateF, DateTo, Quantity, price, price * Quantity * So_Ngay, "Active", orderDate, category);
        if (!DateF.trim().isEmpty()) {
            shoppingCart.AddtoCart(been);
        }
        float total=0;
        HashMap<String,OrdersBeen> items= shoppingCart.getItems();
        for(String CarID: items.keySet()){
            total= total+ items.get(CarID).getTotal();
        }
        sess.put("totalSeSS", total);
        sess.put("CART", shoppingCart);

        return "success";
    }

}
