/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import Entity.LichThi;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rexviet
 */
public class LichThiManager {
    // <editor-fold defaultstate="collapsed" desc="SingletonPattern">
    private LichThiManager () {
    }
     @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }
    protected Object readResolve() {
        return getInstance();
    }
    private static class Nested {
        private static final LichThiManager instance = new LichThiManager ();
    }
    public static LichThiManager getInstance() {
        return Nested.instance;
    }
    // </editor-fold>
    
    private List<LichThi> dsLichThi = new ArrayList<>();

    public List<LichThi> getDsLichThi() {
        return dsLichThi;
    }
    
    public void Clear () {
        this.dsLichThi.clear();
    }
    
    public void sortByDate () {
        Collections.sort(dsLichThi, (Object o1, Object o2) -> {
            try {
                Long time1 = Formatter.getInstance().dateToLong(((LichThi) o1).getNgay());
                Long time2 = Formatter.getInstance().dateToLong(((LichThi) o2).getNgay());
                if (Objects.equals(time1, time2)) {
                    Double ca1 = Double.parseDouble(((LichThi)o1).getCa());
                    Double ca2 = Double.parseDouble(((LichThi)o2).getCa());
                    return ca1.compareTo(ca2);
                }
                return (time1.compareTo(time2));
            } catch (ParseException ex) {
                Logger.getLogger(LichThiManager.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        });
    }
    
    public Vector getDataVector () {
        Vector lichThi = new Vector ();
        this.sortByDate();
        for (LichThi lt : dsLichThi)
            lichThi.add(lt.toVector());
        return lichThi;
    }
}
