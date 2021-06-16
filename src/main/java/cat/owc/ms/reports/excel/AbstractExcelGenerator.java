package cat.owc.ms.reports.excel;


import cat.owc.ms.reports.ReportsClass;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class AbstractExcelGenerator extends ReportsClass {


    protected XSSFWorkbook createWorkbook() {
        return new XSSFWorkbook();
    }


    protected XSSFSheet addShettToWorkbook(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        return sheet;
    }




    /** Metodo para añadir una fila al excel
     *
     * @param sheet - La hoja
     * @param cells - Los datos a añadir
     * @param rowindex - El número de fila
     * @param offset - Offset dentro de la fila donde deben empezar los datos
     */
    protected void addRow(XSSFSheet sheet, List<String> cells, int rowindex, int offset){

        XSSFRow row = sheet.getRow(rowindex);
        if (row == null) {
            row = sheet.createRow((short) rowindex);
        }

        int colNum = offset;
        for (String cellContent : cells) {
            row.createCell(colNum++).setCellValue(cellContent);
        }
    }


    protected void setCellValue(XSSFSheet sheet, Object value, int rowIndex, int colIndex){

        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow((short) rowIndex);
        }

        CellType cellType;
        if (value instanceof Integer)  {
            row.createCell(colIndex, CellType.NUMERIC).setCellValue(new Double((Integer)value));
        }
        else if (value instanceof Double) {
            row.createCell(colIndex, CellType.NUMERIC).setCellValue(new Double((Double)value));
        }
        else if (value instanceof Boolean) {
            row.createCell(colIndex, CellType.BOOLEAN).setCellValue((Boolean) value);
        }
        else {
            row.createCell(colIndex, CellType.STRING).setCellValue((String) value);
        }



    }


    /**
     * Convierte el excel en un stream de bytes
     * @param workbook
     * @return
     */
    protected ByteArrayOutputStream workbookToBos(XSSFWorkbook workbook) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            workbook.write(bos);
        } catch (IOException e) {
            notifyError(IExcelGeneratorErrorCode.EXCEL_GENERATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            try {
                if (workbook != null)
                    workbook.close();
                if (bos != null)
                    bos.close();
            }
            catch (IOException e) {}
        }

        return bos;
    }



}

