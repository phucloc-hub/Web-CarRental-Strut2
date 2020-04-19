/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import loctp.CarRental.AccountDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Loc
 */
public class StartupAction {

    private final String SUCCESS = "success";

    public StartupAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        String email = "", password = "";
        HttpServletRequest req = ServletActionContext.getRequest();
        Cookie[] coki = req.getCookies();
        if (coki != null) {
            for (Cookie c : coki) {

                // lay tung cai ra
                if (c.getName().equals("email")) {
                    email = c.getValue();
                }
                if (c.getName().equals("password")) {
                    password = c.getValue();
                }

                AccountDAO dao = new AccountDAO();
                String rs = dao.checkLogin(email, password);//
                int point = rs.indexOf(':');
                if (!rs.isEmpty()) {
                    String username = rs.substring(0, point).trim();
                    String Role = rs.substring(point + 1).trim();
                    Map sess = ActionContext.getContext().getSession();
                    sess.put("Email", email);
                    sess.put("Role", Role.trim());
                    sess.put("Username", username);

                    break;

                }

            }
        }
        return result;
    }

}
