/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.CarRental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import loctp.Utils.DBUtils;

/**
 *
 * @author Loc
 */
public class OrdersDAO {

    private ArrayList<OrdersDTO> listdto;

    public ArrayList<OrdersDTO> getListdto() {
        return listdto;
    }

    public void insertOrder(String email, String carID, String rentalDate, String returnDate, int quantity, float price, float total, String codeDis, String status, String orderDate) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;

        try {

            con = DBUtils.makeCon();

            String sql = "insert into Orders(Email,CarID,RentalDate,ReturnDate,Quantity,Price,Total,CodeDis,Status,OrderDate) Values(?,?,?,?,?,?,?,?,?,?)";

            prs = con.prepareStatement(sql);
            prs.setString(1, email);
            prs.setString(2, carID);
            prs.setString(3, rentalDate);
            prs.setString(4, returnDate);
            prs.setInt(5, quantity);
            prs.setFloat(6, price);
            prs.setFloat(7, total);
            prs.setString(8, codeDis);

            prs.setString(9, status);
            prs.setString(10, orderDate);

            prs.executeUpdate();

        } finally {
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

}
