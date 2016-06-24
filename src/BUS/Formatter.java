/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rexviet
 */
public class Formatter {
    // <editor-fold defaultstate="collapsed" desc="SingletonPattern">
    private Formatter () {
        this.sdf = new SimpleDateFormat ("dd-MM-yyyy");
    }
     @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }
    protected Object readResolve() {
        return getInstance();
    }
    private static class Nested {
        private static final Formatter instance = new Formatter ();
    }
    public static Formatter getInstance() {
        return Nested.instance;
    }
    // </editor-fold>
    
    private final SimpleDateFormat sdf;
    
    public String FormatDate (String inp) {
        return inp.split(":")[1];
    }
    
    public Date stringToDate (String inp) throws ParseException {
        return sdf.parse(inp);
    }
    
    public long dateToLong (String inp) throws ParseException {
        return sdf.parse(inp).getTime();
    }
    
    public String FormatRoom (String inp) {
        return inp.split(":")[1];
    }
    
    public String ClassKeyToSubjectKey (String inp) {
        return inp.split("\\.")[0];
    }
}
