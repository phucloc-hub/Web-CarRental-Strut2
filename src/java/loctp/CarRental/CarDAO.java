/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.CarRental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import loctp.Utils.DBUtils;

/**
 *
 * @author Loc
 */
public class CarDAO {

    private ArrayList<CarDTO> listCarDTO;

    public ArrayList<CarDTO> getListCarDTO() {
        return listCarDTO;
    }

    //CHECK OUT 
    public int getQuantity_Left(String DateF, String DateTo, String carID) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        int quantity_left = -1;
        try {
            con = DBUtils.makeCon();

            String sql2 = "select (Car.Quantity- T.qt) as carLeft from Car ,(\n"
                    + "\n"
                    + " select Orders.CarID,Sum(Quantity) as qt from Orders\n"
                    + " where (? between RentalDate and ReturnDate) or (? between RentalDate and ReturnDate)\n"
                    + "  or ? <= RentalDate or ? >= ReturnDate\n"
                    + "  group by CarID\n"
                    + "  having (select Car.Quantity from Car where Car.CarID=Orders.CarID) - SUM(Orders.Quantity)>0)\n"
                    + "  as T \n"
                    + "  where Car.CarID=T.CarID and Car.CarID= ? ";
            prs = con.prepareStatement(sql2);
            prs.setString(1, DateF);
            prs.setString(2, DateTo);
            prs.setString(3, DateF);
            prs.setString(4, DateTo);
            prs.setString(5, carID);

            rs = prs.executeQuery();
            while (rs.next()) {
                quantity_left = rs.getInt(1);

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return quantity_left;
    }

    //GET QUANTITY IN CAR TABLE
    public int getQuantity(String carID) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtils.makeCon();

            String sql2 = "select Quantity from Car where CarID= ? ";
            prs = con.prepareStatement(sql2);

            prs.setString(1, carID);

            rs = prs.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // FIND QUANTITY OF a Product
    public int findQuantity_Left(String DateF, String DateTo, int quantityIncar, String carID) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeCon();

//            String sql = "select ProductName,Quantity,Total,BuyDate,Price from [Order] where Email = ? AND ProductName LIKE ?";
            String sql2 = " select Sum(Quantity) as qt from Orders\n"
                    + "                     where (? between RentalDate and ReturnDate) or (? between RentalDate and ReturnDate)\n"
                    + "                      or (? >= RentalDate and ? <= ReturnDate) \n"
                    + "                      group by CarID\n"
                    + "					  having CarID=?";
            prs = con.prepareStatement(sql2);
            prs.setString(1, DateF);
            prs.setString(2, DateTo);
            prs.setString(3, DateF);
            prs.setString(4, DateTo);
            prs.setString(5, carID);

            rs = prs.executeQuery();
            if (rs.next()) {
                int Ordered_quantity = rs.getInt(1);
                quantityIncar = quantityIncar - Ordered_quantity;

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return quantityIncar;
    }

// SSEARCH USER
    public void searchbyName2(String DateF, String DateTo, int Quantity, String Name) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        listCarDTO = new ArrayList<>();
        try {
            con = DBUtils.makeCon();

//            String sql = "select ProductName,Quantity,Total,BuyDate,Price from [Order] where Email = ? AND ProductName LIKE ?";
            String sql2 = " select Car.CarID,CarName,Color,Car.Year,Price,Car.CategoryID,Quantity from Car\n"
                    + "					  where CarName like ?";
            prs = con.prepareStatement(sql2);
            prs.setString(1, "%" + Name + "%");

            rs = prs.executeQuery();
            while (rs.next()) {
                String CarID = rs.getString(1);
                String CarName = rs.getString(2);
                String Coloe = rs.getString(3);
                String Year = rs.getString(4);
                float Price = rs.getFloat(5);
                int CategoryID = rs.getInt(6);
                int quantity = rs.getInt(7);
                int quantity_update = findQuantity_Left(DateF, DateTo, quantity, CarID);
                if (quantity_update > 0) {
                    listCarDTO.add(new CarDTO(CarID, CarName, Coloe, Year, CategoryID, Price, quantity_update, "Active"));
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchbyName(String DateF, String DateTo, int Quantity, String Name) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        listCarDTO = new ArrayList<>();
        try {
            con = DBUtils.makeCon();

//            String sql = "select ProductName,Quantity,Total,BuyDate,Price from [Order] where Email = ? AND ProductName LIKE ?";
            String sql2 = "select Car.CarID,CarName,Color,Car.Year,Price,Car.CategoryID,(Car.Quantity- T.qt) as Quantity from Car,(\n"
                    + "\n"
                    + " select Orders.CarID,Sum(Quantity) as qt from Orders\n"
                    + " where (? between RentalDate and ReturnDate) or (? between RentalDate and ReturnDate)\n"
                    + "  or ? <= RentalDate or ? >= ReturnDate \n"
                    + "  group by CarID\n"
                    + "  having (select Car.Quantity from Car where Car.CarID=Orders.CarID) - SUM(Orders.Quantity) - ?>0)\n"
                    + "  as T \n"
                    + "  where Car.Status='Active' and Car.CarID=T.CarID and Car.CarName like ?";
            prs = con.prepareStatement(sql2);
            prs.setString(1, DateF);
            prs.setString(2, DateTo);
            prs.setString(3, DateF);
            prs.setString(4, DateTo);
            prs.setInt(5, Quantity);

            prs.setString(6, "%" + Name + "%");

            rs = prs.executeQuery();
            while (rs.next()) {
                String CarID = rs.getString(1);
                String CarName = rs.getString(2);
                String Coloe = rs.getString(3);
                String Year = rs.getString(4);
                float Price = rs.getFloat(5);
                int CategoryID = rs.getInt(6);
                int quantity = rs.getInt(7);

                listCarDTO.add(new CarDTO(CarID, CarName, Coloe, Year, CategoryID, Price, quantity, "Active"));

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    ///
    public void searchbyCategory2(String DateF, String DateTo, int Quantity, String Category) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        listCarDTO = new ArrayList<>();
        try {
            con = DBUtils.makeCon();

//            String sql = "select ProductName,Quantity,Total,BuyDate,Price from [Order] where Email = ? AND ProductName LIKE ?";
            String sql2 = " select Car.CarID,CarName,Color,Car.Year,Price,Car.CategoryID,Quantity from Car\n"
                    + "					  where Car.CategoryID in (select Category.ID from Category where Category.Category like ? ) ";
            prs = con.prepareStatement(sql2);
            prs.setString(1, "%" + Category + "%");

            rs = prs.executeQuery();
            while (rs.next()) {
                String CarID = rs.getString(1);
                String CarName = rs.getString(2);
                String Coloe = rs.getString(3);
                String Year = rs.getString(4);
                float Price = rs.getFloat(5);
                int CategoryID = rs.getInt(6);
                int quantity = rs.getInt(7);
                int quantity_update = findQuantity_Left(DateF, DateTo, quantity, CarID);
                if (quantity_update > 0) {
                    listCarDTO.add(new CarDTO(CarID, CarName, Coloe, Year, CategoryID, Price, quantity_update, "Active"));
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchbyCategory(String DateF, String DateTo, int Quantity, String Category) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        listCarDTO = new ArrayList<>();
        try {
            con = DBUtils.makeCon();

//            String sql = "select ProductName,Quantity,Total,BuyDate,Price from [Order] where Email = ? AND ProductName LIKE ?";
            String sql2 = "select Car.CarID,CarName,Color,Car.Year,Price,Car.CategoryID,(Car.Quantity- T.qt) as Quantity from Car,Car.CartypeID ,(\n"
                    + "\n"
                    + " select Orders.CarID,Sum(Quantity) as qt from Orders\n"
                    + " where (? between RentalDate and ReturnDate) or (? between RentalDate and ReturnDate)\n"
                    + "  or ? <= RentalDate or ? >= ReturnDate \n"
                    + "  group by CarID\n"
                    + "  having (select Car.Quantity from Car where Car.CarID=Orders.CarID) - SUM(Orders.Quantity) - ?>0)\n"
                    + "  as T \n"
                    + "  where Car.Status='Active' and Car.CarID=T.CarID and Car.CategoryID in (select Category.ID from Category where Category.Category like ?)";
            prs = con.prepareStatement(sql2);
            prs.setString(1, DateF);
            prs.setString(2, DateTo);
            prs.setString(3, DateF);
            prs.setString(4, DateTo);
            prs.setInt(5, Quantity);

            prs.setString(6, "%" + Category + "%");

            rs = prs.executeQuery();
            while (rs.next()) {
                String CarID = rs.getString(1);
                String CarName = rs.getString(2);
                String Coloe = rs.getString(3);
                String Year = rs.getString(4);
                float Price = rs.getFloat(5);
                int CategoryID = rs.getInt(6);
                int quantity = rs.getInt(7);

                listCarDTO.add(new CarDTO(CarID, CarName, Coloe, Year, CategoryID, Price, quantity, "Active"));

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

}
