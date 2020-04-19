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
public class DiscountsDAO {
    
     public DiscountsDTO getPercent(String codeDis) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        DiscountsDTO dto=null;
        
        int result = 0;
        try {
            con = DBUtils.makeCon();

            String sql2 = "select CodeDis,ExpiryDate,Percents from Discounts where CodeDis= ? ";
            prs = con.prepareStatement(sql2);

            prs.setString(1, codeDis);

            rs = prs.executeQuery();
            while (rs.next()) {
                String ExpiryDate = rs.getString(2);
                int Percents= rs.getInt(3);
                dto = new DiscountsDTO(codeDis, ExpiryDate, Percents);

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
        return dto;
    }
    
}
