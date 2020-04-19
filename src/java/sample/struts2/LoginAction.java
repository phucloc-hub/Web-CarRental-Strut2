/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import loctp.CarRental.AccountDAO;
import loctp.CarRental.AccountDTO;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Loc
 */
public class LoginAction extends ActionSupport implements ServletResponseAware {

    private String email;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    protected HttpServletResponse servletResponse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginAction() {
    }

    @Override
    public String execute() throws Exception {
        String url = FAIL;

        AccountDAO dao = new AccountDAO();
        if (name.isEmpty()) { // name empty is login traditional
            String result = dao.checkLogin(email, password);
            if (result.length() != 0) {
                url = SUCCESS;

                int point = result.indexOf(':');
                String username = result.substring(0, point).trim();
                String Role = result.substring(point + 1).trim();
                Map sess = ActionContext.getContext().getSession();
                sess.put("Username", username);
                sess.put("Role", Role);
                sess.put("Email", email);

                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
                servletResponse.addCookie(cookie);
                cookie = new Cookie("password", password);
                cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
                servletResponse.addCookie(cookie);
//                sess.setAttribute("CurCo0kie", cookie);

            }
        } else {// name is NOT empty is login with google
            url = SUCCESS;

            AccountDTO dto = dao.getExistAccount(email);
            Map sess = ActionContext.getContext().getSession();
            if (dto == null) {// create new 
                dao.addAccount(email, name, "1", "Member");

                sess.put("Role", "Member");
                password = "1";
            } else {
                sess.put("Role", dto.getRole().trim());

                password = dto.getPassword();
            }
            sess.put("Username", name);
            sess.put("Email", email);

            Cookie cookie = new Cookie("email", email);
            cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
            servletResponse.addCookie(cookie);
            cookie = new Cookie("password", password);
            cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
            servletResponse.addCookie(cookie);

        }

        return url;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        this.servletResponse = hsr;

    }

}
