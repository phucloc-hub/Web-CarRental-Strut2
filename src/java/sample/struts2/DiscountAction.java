/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import loctp.CarRental.DiscountsDAO;
import loctp.CarRental.DiscountsDTO;
import loctp.CarRental.OrdersBeen;
import loctp.cart.Cart;

/**
 *
 * @author Loc
 */
public class DiscountAction extends ActionSupport {

    private String codeDis;

    public String getCodeDis() {
        return codeDis;
    }

    public void setCodeDis(String codeDis) {
        this.codeDis = codeDis;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public DiscountAction() {

    }

    @Override
    public void validate() {
        if (codeDis.trim().length() == 0) {
            addFieldError("codeDis", "Input Code");
        }

    }

    public String execute() throws Exception {
        String result = "success";
        DiscountsDAO dao = new DiscountsDAO();
        DiscountsDTO dto = dao.getPercent(codeDis);
        Map sess = ActionContext.getContext().getSession();

        if (dto != null) {
            String date = dto.getExpiryDate();
            String now = LocalDate.now().toString();
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            Date da = Date.valueOf(date);
            Date no = Date.valueOf(now);
            c1.setTime(da);
            c2.setTime(no);
            if (da.after(no)) {
                float totalSeSS = (float) sess.get("totalSeSS");
                sess.put("CodeDTO", dto);
                sess.put("totalss", (totalSeSS - (totalSeSS * dto.getPercents() / 100)));

            } else {
                sess.remove("totalss");
                sess.put("EXPIRY_DATE", "EXPIRY");
            }

        } else {
            sess.put("errorCode", "Error");
        }

        return result;
    }

}
