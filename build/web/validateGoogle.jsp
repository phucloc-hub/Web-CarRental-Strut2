<%-- 
    Document   : ValidateGoogle
    Created on : Mar 13, 2020, 10:12:30 AM
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
        Enter your Code from your Google's mail<br>
        <form action="ValidateAction" method="POST">
            <input type="hidden" name="email" value="<s:property value="email"/>" />
            <input type="hidden" name="name" value="<s:property value="name"/>" />
            <input type="hidden" name="password" value="<s:property value="password"/>" />
            <input type="hidden" name="phone" value="<s:property value="phone"/>" />
            <input type="hidden" name="address" value="<s:property value="address"/>" />
            <s:textfield name="code"/>
            <input type="submit"/>
        </form>

    </body>
</html>
