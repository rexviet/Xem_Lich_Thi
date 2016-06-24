/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Vector;

/**
 *
 * @author Rexviet
 */
public class LichThi {
    private String maMH, tenMH, SBD, Phong, Ngay, Ca;

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getMaMH() {
        return maMH;
    }
    
    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }
    
    public String getTenMH() {
        return tenMH;
    }
    
    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }
    
    public String getSBD() {
        return SBD;
    }
    
    public void setSBD(String SBD) {
        this.SBD = SBD;
    }
    
    public String getPhong() {
        return Phong;
    }
    
    public void setPhong(String Phong) {
        this.Phong = Phong;
    }
    
    public String getNgay() {
        return Ngay;
    }
    
    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getCa() {
        return Ca;
    }

    public void setCa(String Ca) {
        this.Ca = Ca;
    }
    
//</editor-fold>
    
    public Vector toVector () {
        Vector lichThi = new Vector ();
        
        lichThi.addElement(maMH);
        lichThi.addElement(tenMH);
        lichThi.addElement(SBD);
        lichThi.addElement(Phong);
        lichThi.addElement(Ngay);
        lichThi.addElement(Ca.split("\\.")[0]);
        
        return lichThi;
    }
    
    public String getValueAt (int i) {
        switch (i) {
            case 0: return this.maMH;
            case 1: return this.tenMH;
            case 2: return this.SBD;
            case 3: return this.Phong;
            case 4: return this.Ngay;
            case 5: return this.Ca;
        }
        return "";
    }
}
