package rahulshettyacademy.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rajiacademy.AbstractComponents.ProductDetails;

public class ExcelReaderUtility {

public ArrayList<ProductDetails> getDataFromExcel(String filePath) throws IOException {
		
		FileInputStream fileInput = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workbook.getSheetAt(0);
		ArrayList<ProductDetails> productList = new ArrayList<ProductDetails>();
		
		for(Row row:sheet) {
			Cell browsercell = row.getCell(0);
			Cell productNamecell = row.getCell(1);
			Cell countrycell = row.getCell(2);
			
			if(browsercell!=null && productNamecell!=null && countrycell!=null)
			{
				ProductDetails p = new ProductDetails();
				
				p.setBrowser(browsercell.getStringCellValue());
				p.setProductName(productNamecell.getStringCellValue());
				p.setCountry(countrycell.getStringCellValue());
				
				productList.add(p);
				
				System.out.println("Data read from Excel successfully");
			}
		}
		
		return productList;
		
		
	}
}
