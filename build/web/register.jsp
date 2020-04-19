<%-- 
    Document   : register
    Created on : Mar 13, 2020, 8:38:47 AM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
   
        
    </head>
    <body>
        <form action="RegisterAction" method="POST">
            Email <s:textfield name="email"/><br>
            Name <s:textfield name="name"/><br>
            Password <s:textfield name="password"/><br>
            rePassword <s:textfield name="rePassword"/><br>
            Phone <s:textfield name="phone"/><br>
            Address <s:textfield name="address"/><br>
            <s:submit value="submit"/>   
        </form>


    </body>
</html>
