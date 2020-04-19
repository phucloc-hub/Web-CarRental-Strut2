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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Loc
 */
public class LogoutAction extends ActionSupport implements ServletResponseAware {

    private final String SUCCESS = "success";
    protected HttpServletResponse servletResponse;

    public LogoutAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        Map sess = ActionContext.getContext().getSession();

        sess.remove("Username");
        sess.remove("Role");
        sess.remove("Email");
        
        HttpServletRequest req= ServletActionContext.getRequest();
        
        for(Cookie c: req.getCookies()){
            c.setMaxAge(0);
            c.setValue("");
            servletResponse.addCookie(c);
        }
        
        return result;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        this.servletResponse = hsr;
    }

}
