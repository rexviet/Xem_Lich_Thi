/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Rexviet
 */
public class XlsxFileFilter implements FilenameFilter {
    // <editor-fold defaultstate="collapsed" desc="SingletonPattern">
    private XlsxFileFilter () {
    }
     @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }
    protected Object readResolve() {
        return getInstance();
    }
    private static class Nested {
        private static final XlsxFileFilter instance = new XlsxFileFilter ();
    }
    public static XlsxFileFilter getInstance() {
        return Nested.instance;
    }
    // </editor-fold>
   
    @Override
    public boolean accept(File dir, String name) {
        return ((name.endsWith("xls") || name.endsWith("xlsx")) && !name.startsWith("~") && !name.startsWith("lichthi"));
    }
}
