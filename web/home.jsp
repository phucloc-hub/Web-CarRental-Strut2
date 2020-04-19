<%-- 
    Document   : home
    Created on : Mar 8, 2020, 1:06:55 PM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home page</h1>
        <s:if test="%{#session.Username != null}">
            <font style="color: red">
            Wellcome, <s:property value="%{#session.Username}"/>

            </font> 
            <s:a action="LogoutAction">-.Logout.-</s:a>
        </s:if>
        <s:else>
            <a href="login.html">Login</a>
        </s:else>

        <header>
            <form action="SearchAction">
                <input type="radio" name="options" checked value="Name"> Name<br>
                <input type="radio" name="options" value="Category"> Category<br>
                <input type="text" name="Name" value="" placeholder="Name..." />
                <input type="text" name="Category" value="" placeholder="Category..." />
                <input type="date" name="DateF" value="${param.DateF}" />
                <input type="date" name="DateTo" value="${param.DateTo}" />
                <input type="text" name="Quantity" value="1" />   
                <input type="submit" />
            </form>

        </header>
                
                <s:if test="%{#session.Username != null}">
                    <a href="myCart.jsp">View-Cart</a>         
                </s:if>     
        

        <s:if test="%{#session.RESULT != null}">
            <s:set var="RESULT" value="%{#session.RESULT}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Car Name</th>
                        <th> Color </th>
                        <th>Year</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Get-iT</th>

                    </tr>
                </thead>
                <tbody>

                    <s:iterator var="car" value="%{RESULT}">

                        <tr>
                            <td><s:property value="carName"/></td>
                            <td><s:property value="color"/></td>
                            <td><s:property value="year"/></td>
                            <td><s:property value="price"/></td>
                            <td><s:property value="categoryID"/></td>
                            <td><s:property value="quantity"/></td>

                            <td>
                                <s:if test="%{#session.Username != null}">
                                    <form action="AddtoCartAction" method="POST">
                                        <s:hidden name="carName" value="%{carName}"/>
                                        <input type="hidden" name="categoryID" value="<s:property value="categoryID"/>" />
                                        <input type="hidden" name="DateF" value="${DateF}" /> 
                                        <input type="hidden" name="DateTo" value="${DateTo}" />
                                        <input type="submit"/>
                                    </form>
                                </s:if>
                             <s:else>
                                Login To Buy~
                            </s:else>   
                            </td>
                            
                        </tr>
                    </s:iterator>
                </tbody>
            </table>




        </s:if>
        <s:elseif test="RESULT == null and Quantity != null">
            no record
        </s:elseif>
    </body>
</html>
