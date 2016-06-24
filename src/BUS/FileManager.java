/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import Entity.LichThi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rexviet
 */
public class FileManager {
    private File fileLichThi;
    
    // <editor-fold defaultstate="collapsed" desc="SingletonPattern">
    private FileManager () {
        fileLichThi = new File ("lichthi.xls");
        if (!fileLichThi.exists() || fileLichThi == null)
            fileLichThi = new File ("lichThi.xlsx");
    }
     @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }
    protected Object readResolve() {
        return getInstance();
    }
    private static class Nested {
        private static final FileManager instance = new FileManager ();
    }
    public static FileManager getInstance() {
        return Nested.instance;
    }
    // </editor-fold>
    
    public void DocDanhSachPhongThi (File f, String MSSV) throws IOException, FileNotFoundException, InvalidFormatException {
        Sheet sheet;
        if (f.getName().endsWith(".xlsx")) {
            sheet = (XSSFSheet) readXLSXFile (f);
        } else
            sheet = (HSSFSheet) readXLSFile (f);
        int SBD = this.timSBD(MSSV, sheet);
        if (SBD != -1)
            this.AddThisSheet(SBD, sheet);
    }
    
    public Sheet readXLSXFile (File f) throws FileNotFoundException, IOException, InvalidFormatException {
        InputStream ExcelFileToRead = new FileInputStream(f);
        //System.out.println(f.getName());
        XSSFWorkbook  wb = (XSSFWorkbook) WorkbookFactory.create(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheetAt(0);
        return sheet;
    }
    
    private Sheet readXLSFile (File f) throws FileNotFoundException, IOException, InvalidFormatException {
        InputStream ExcelFileToRead = new FileInputStream(f);
        HSSFWorkbook wb = (HSSFWorkbook) WorkbookFactory.create(ExcelFileToRead);
        HSSFSheet sheet = wb.getSheetAt(0);
        return sheet;
    }
    
    private int timSBD (String MSSV, Sheet sheet) {
        int row = 10;
        String valueAtRow;
        do {
            valueAtRow = sheet.getRow(row).getCell(1).getStringCellValue();
            if (valueAtRow.equals(MSSV))
                return (row - 9);
            row++;
        } while (!valueAtRow.isEmpty());
        return -1;
    }
    
    private void AddThisSheet (int SBD, Sheet sheet) throws IOException, FileNotFoundException, InvalidFormatException {
        LichThi lt = new LichThi ();
        lt.setMaMH(sheet.getRow(5).getCell(4).getStringCellValue());
        lt.setTenMH(sheet.getRow(5).getCell(2).getStringCellValue());
        lt.setSBD(Integer.toString(SBD));
        lt.setPhong(Formatter.getInstance().FormatRoom(sheet.getRow(5).getCell(5).getStringCellValue()));
        lt.setNgay(Formatter.getInstance().FormatDate(sheet.getRow(4).getCell(5).getStringCellValue()));
        lt.setCa(this.timCaThi(Formatter.getInstance().ClassKeyToSubjectKey(lt.getMaMH())));
        //Data.add(lt.toVector());
        LichThiManager.getInstance().getDsLichThi().add(lt);
    }
    
    private String timCaThi (String maMH) throws IOException, FileNotFoundException, InvalidFormatException {
        Sheet sheet;
        if (fileLichThi.exists()) {
            if (fileLichThi.getName().endsWith(".xls"))
                sheet = (HSSFSheet) this.readXLSFile(fileLichThi); 
            else 
                sheet = (XSSFSheet) this.readXLSXFile(fileLichThi);
            int row = 8;
            String valAtRow;
            do {
                valAtRow = sheet.getRow(row).getCell(1).getStringCellValue();
                if (valAtRow.equals(maMH)) {
                    return Double.toString(sheet.getRow(row).getCell(9).getNumericCellValue());
                }
                row++;
            } while (!valAtRow.isEmpty());
        }
        return "";  
    }
    
    public void exportToXLSXFile (String fileName) throws FileNotFoundException, IOException {
        XSSFWorkbook wb = new XSSFWorkbook ();
        XSSFSheet sheet = wb.createSheet();
        List<LichThi> Data = LichThiManager.getInstance().getDsLichThi();
        int rowNum = 0;
        // set title
        Row row = sheet.createRow(rowNum++);
        Cell cell = row.createCell(0);
        cell.setCellValue("Mã Lớp");
        cell = row.createCell(1);
        cell.setCellValue("Tên MH");
        cell = row.createCell(2);
        cell.setCellValue("SBD");
        cell = row.createCell(3);
        cell.setCellValue("Phòng");
        cell = row.createCell(4);
        cell.setCellValue("Ngày");
        cell = row.createCell(5);
        cell.setCellValue("Ca");
        
        // set contents
        for (LichThi lt : Data) {
            row = sheet.createRow(rowNum++);
            int cellNum = 0;
            
            for (int i=0; i<6; i++) {
                cell = row.createCell(cellNum++);
                cell.setCellValue(lt.getValueAt(i));
            }
        }
        
        try (FileOutputStream fos = new FileOutputStream(new File (fileName))) {
            wb.write(fos);
            fos.close();
        }
    }
}
