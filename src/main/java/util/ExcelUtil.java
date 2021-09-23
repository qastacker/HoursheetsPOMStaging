package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class ExcelUtil {

	public static String TEST_DATA_SHEET_PATH = "./src/main/java/testData/HoursheetsTestData.xlsx"; // Location of Test data excel file
	private static Workbook excelWBook; // Excel WorkBook
	private static Sheet excelWSheet; // Excel Sheet
	private static Cell cell; // Excel cell
	public static int rowNumber; // Row Number
	public static int columnNumber; // Column Number

	public ExcelUtil(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	// This method has two parameters: "Test data excel file name" and "Excel sheet
	// name"
	// It creates FileInputStream and set excel file and excel sheet to excelWBook
	// and excelWSheet variables.
	public static  String getCellData(String sheetName,int RowNum, int ColNum) {

		// Open the Excel file
		FileInputStream file = null;

		try {
			file = new FileInputStream(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			excelWBook = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		excelWSheet = excelWBook.getSheet(sheetName);

	// This method reads the test data from the Excel cell.
	// We are passing row number and column number as parameters.
		cell = excelWSheet.getRow(RowNum).getCell(ColNum);
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

}
