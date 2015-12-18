package xmlpaser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
 
public class ReadabilityExcelReader {
 


    List<ReadabilityData> readXls(String fileName) throws IOException {
        InputStream is = new FileInputStream("results/"+fileName+"_funcBodyReadability.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ReadabilityData readabilityData = null;
        List<ReadabilityData> list = new ArrayList<ReadabilityData>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                readabilityData = new ReadabilityData();

                HSSFCell className = hssfRow.getCell(0);
                if (className == null) {
                    continue;
                }
                readabilityData.setClassName(getValue(className));
                
                HSSFCell readability = hssfRow.getCell(1);
                if (readability == null) {
                    continue;
                }
                readabilityData.setReadability(getValue(readability));
                
                
                
                list.add(readabilityData);
            }
        }
        return list;
    }
 

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
 
}