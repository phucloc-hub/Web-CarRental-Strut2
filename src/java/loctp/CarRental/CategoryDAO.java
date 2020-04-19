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
import javax.naming.NamingException;
import loctp.Utils.DBUtils;

/**
 *
 * @author Loc
 */
public class CategoryDAO {

    public String getCategory(int categoryid) throws SQLException, ClassNotFoundException, NamingException {

        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        String category = "";
        try {
            //1. Make Connection
            con = DBUtils.makeCon();

            if (con != null) {
                //2. tao cau truy van
                String sql = "select Category\n"
                        + "            From Category \n"
                        + "           Where Category.ID = ?";

                //3. Tao statement and set parameter
                prs = con.prepareStatement(sql);
                prs.setInt(1, categoryid);

                //4. Execute query
                rs = prs.executeQuery();

                //5. process rs
                if (rs.next()) {// co hay k co. rs luon luon lon hon bang 0. khong co thi =0.
                    category = rs.getString(1);

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
        return category;
    }

}
