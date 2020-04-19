/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.time.LocalDate;
import java.util.Map;
import loctp.CarRental.AccountDAO;

/**
 *
 * @author Loc
 */
public class ValidateAction extends ActionSupport{

    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String code;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ValidateAction() {
    }

    @Override
    public void validate() {
        Map sess = ActionContext.getContext().getSession();
        String reCODE =  sess.get("CODE").toString();
        if(!reCODE.equals(code)){
            addFieldError("code", "Not macth with the code");
        }
    }

    
    public String execute() throws Exception {
        String result = SUCCESS;

        AccountDAO dao = new AccountDAO();
        
        
            String CreateDate = LocalDate.now().toString();
            boolean rs = dao.createAccount(email, name, password, "Member", phone, address, CreateDate);
            if (rs == false) {
                result = FAIL;
            }
       
        return result;
    }

}
