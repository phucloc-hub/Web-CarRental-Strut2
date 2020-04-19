/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import loctp.CarRental.AccountDAO;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Loc
 */
public class RegisterAction extends ActionSupport {

    private String email;
    private String password;
    private String rePassword;
    private final String SUCCESS = "success";
    private final String FAIL="fail";

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    private String name;
    private String phone;
    private String address;

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

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public RegisterAction() {
    }

    @Override
    public void validate() {
        boolean check = false;
        if (!email.matches("\\w{1,10}[@]\\w{1,7}[.]\\w{1,7}([.]\\w{1,7}){0,1}")) {
            addFieldError("email", "Email must be valid");
            check = true;
        }
        AccountDAO dao = new AccountDAO();

        try {
            if (dao.isExistEmail(email) && check == false) {
                addFieldError("email", "This email is exist");
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            java.util.logging.Logger.getLogger(RegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (name.isEmpty() || name == null) {
            addFieldError("name", "Name is required");
        }
        if (password.length() < 6) {
            addFieldError("password", "Password must be greater than 6 characters");
        }
        if (!phone.matches("[0-9]{3,10}")) {
            addFieldError("phone", "Not valid phone");

        }
        if (address.isEmpty() || address == null) {
            addFieldError("address", "Address is required");
        }
        if (!rePassword.equals(password)) {
            addFieldError("rePassword", "Not match with the password");

        }

    }

    public static boolean send(String from, String password, String to, String sub, String msg) {
        boolean result = true;
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            result=false;
            throw new RuntimeException(e);
        }
        return result;

    }

    public static int getRandomInteger(int maximum, int minimum){ return ((int) (Math.random()*(maximum - minimum))) + minimum; }

    
    
    @Override
    public String execute() throws Exception {
        String result = SUCCESS;
        // thuc hien send email code
        int RanDomNum= getRandomInteger(1000, 9999);
        boolean rs= send("thaopham2091@gmail.com", "Anhemlaso3", email, "Validation Email", "your code: "+RanDomNum);
        Map sess= ActionContext.getContext().getSession();
        sess.put("CODE", RanDomNum);
        if(rs==false){
            result=FAIL;
        }
        return result;
    }

}
