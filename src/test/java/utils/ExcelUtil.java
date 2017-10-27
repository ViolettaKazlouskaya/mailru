package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtil {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        if (ExcelWSheet == null) {
            System.out.println("ExcelWSheet==null");
            return "";
        } else {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            return Cell.getStringCellValue();
        }
    }

    public static void setCellData(String Result, int RowNum, int ColNum, String path) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static int getNumOfRows() {
        return ExcelWSheet.getLastRowNum();
    }
}
