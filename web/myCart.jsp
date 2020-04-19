<%-- 
    Document   : myCart
    Created on : Mar 16, 2020, 10:48:22 AM
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
        //
        <h1>Your Cart ^^</h1>


        <table border="1">
            <thead>
                <tr>
                    <th>carName</th>
                    <th>Type</th> 
                    <th>Price</th>
                    <th>Total</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>

                <s:if test="%{#session.CART.items != null}">
                    <s:iterator value="%{#session.CART.items}">
                        <tr>
                            <td><s:property value="value.carName"/></td>
                            <td><s:property value="value.category"/></td>  
                            <td><s:property value="value.price"/></td> 
                            <td><s:property value="value.total"/></td>
                            <td><s:property value="value.dateF"/></td>
                            <s:set var="DateF1" value="%{value.dateF}"/>

                            <td><s:property value="value.dateTo"/></td>
                            <s:set var="DateTo1" value="%{value.dateTo}"/>

                    <form action="UpdateQuantityAction">   
                        <td> <input type="text" name="quantity" value="<s:property value="value.quantity"/>" /> </td>
                        <input type="hidden" name="carID" value="<s:property value="value.carID"/>" />
                        <!-- 2 du kien DateF, DateTo de xac dinh dung object key trong HashMap<Orderbeen,Quantity> -->
                        <input type="hidden" name="DateF" value="<s:property value="value.dateF"/>" />
                        <input type="hidden" name="DateTo" value="<s:property value="value.dateTo"/>" />
                        <td><input type="submit" value="Update"/></td>
                    </form>      
                    <form action="DeleteAction" onsubmit="return confirm('Are you sure?')" >
                        <input type="hidden" name="carID" value="<s:property value="value.carID"/>" />
                        <input type="hidden" name="DateF" value="<s:property value="value.dateF"/>" />
                        <input type="hidden" name="DateTo" value="<s:property value="value.dateTo"/>" />
                        <td><input type="submit" value="Delete"/></td>  

                    </form>


                </tr>

            </s:iterator>
                <tr> Total: ${sessionScope.totalSeSS} </tr>
            <tr> <c:if test="${not empty sessionScope.totalss }">
                    After applied discount: ${sessionScope.totalss}
                    
                </c:if>
            </tr>
            <form action="DiscountAction">
                <input type="hidden" name="totals" value="${totals}" />
                Discount: <input type="text" name="codeDis" value="" />
                <input type="submit" value="Apply"/><br>

            </form>
                <c:if test="${not empty sessionScope.EXPIRY_DATE}">
                    This code is end!
                </c:if>
            <tr>
                <s:if test="%{#request.ERROR != null}">
                <font style="color: red">
                ${requestScope.ERROR}
                </font>    
            </s:if>

        </tr>

        <form action="CheckOutAction" method="POST">
            <input type="submit" value="CheckOut"/>   
        </form>


    </s:if>
    <c:url var="urlHome" value="home.jsp" >
        <c:param value="${DateF1}" name="DateF"/>
        <c:param value="${DateTo1}" name="DateTo"/>
    </c:url>
    <s:url var="ho" action="home">

    </s:url>
    <a href="${ho}">HOME</a>

</tbody>
</table>


</body>
</html>
