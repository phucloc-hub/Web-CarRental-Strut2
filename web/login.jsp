<%-- 
    Document   : login
    Created on : Mar 8, 2020, 12:20:24 PM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="LoginAction" method="POST">
           Username <s:textfield name="email" value=""/><br>
           Password <s:password name="password" value=""/><br>
           <font style="color: red">
            This account is not found!!
           </font>
            <s:submit value="Login"/>
        </form>
          
        
        
        
        
        
    </body>
</html>
