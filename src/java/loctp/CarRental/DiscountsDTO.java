/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loctp.CarRental;

import java.io.Serializable;

/**
 *
 * @author Loc
 */
public class DiscountsDTO implements Serializable{
    private String codeDis;
    private String expiryDate;
    private int percents;

    public DiscountsDTO() {
    }

    public DiscountsDTO(String codeDis, String expiryDate, int percents) {
        this.codeDis = codeDis;
        this.expiryDate = expiryDate;
        this.percents = percents;
    }

    public String getCodeDis() {
        return codeDis;
    }

    public void setCodeDis(String codeDis) {
        this.codeDis = codeDis;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }
    
}
