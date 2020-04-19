/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import java.util.Map;
import loctp.CarRental.CarDAO;
import loctp.CarRental.CarDTO;

/**
 *
 * @author Loc
 */
public class SearchAction extends ActionSupport {

    // check box value to know kind of search
    private String Category;
    private String DateF;
    private String DateTo;
    private int Quantity;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    private String options;

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getDateF() {
        return DateF;
    }

    public void setDateF(String DateF) {
        this.DateF = DateF;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String DateTo) {
        this.DateTo = DateTo;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    @Override
    public void validate() {
        if (DateF.isEmpty() || DateF == null) {
            addFieldError("DateF", "Select date");
        }
        if (DateTo.isEmpty() || DateTo == null) {
            addFieldError("DateTo", "Select date");

        }
        if (Quantity < 0) {
            addFieldError("Quantity", "Positive number");
        }

    }

    public SearchAction() {
    }

    public String execute() throws Exception {
        String result = "success";
        CarDAO dao = new CarDAO();
        ArrayList<CarDTO> list = null;
        // get value check box and phan chia

        if (options.equals("Category")) {
            dao.searchbyCategory2(DateF, DateTo, Quantity, Category);
            list = dao.getListCarDTO();
        } else {
            dao.searchbyName2(DateF, DateTo, Quantity, Name);
            list= dao.getListCarDTO();
        }
        // end get value check box

        Map sess = ActionContext.getContext().getSession();
        if (!list.isEmpty()) {

            sess.put("RESULT", list);
        }else{
            sess.remove("RESULT");
        }

        return result;
    }

}
