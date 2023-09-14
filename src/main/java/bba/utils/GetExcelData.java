package bba.utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class GetExcelData {

	public List<Map<String, String>> getExcelData(String fileName) throws Exception {

		ArrayList<Map<String, String>> excelTestData = new ArrayList<Map<String, String>>();
		XSSFWorkbook excelWrookBook;
		XSSFSheet excelWSheet;
		String filePath = null;
		try {
			filePath = String.format("%s/src/test/resources/TestData/%s", System.getProperty("user.dir"), fileName);
			FileInputStream excelFile = new FileInputStream(filePath);

			excelWrookBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWrookBook.getSheet("Blad1");
			int totalRows = excelWSheet.getLastRowNum();

			for (int rowIdx = 1; rowIdx <= totalRows; rowIdx++) {
				Map<String, String> rowMap = new HashMap<String, String>();
				for (int columnIdx = 0; columnIdx <= 3; columnIdx++)
					rowMap.put(excelWSheet.getRow(0).getCell(columnIdx).toString(),
							excelWSheet.getRow(rowIdx).getCell(columnIdx).toString());
				excelTestData.add(rowMap);
			}
			System.out.println(String.format("The data values present in the file %s are %s", fileName, excelTestData));
			excelWrookBook.close();
		} catch (FileNotFoundException exception) {
			System.out.println(
					String.format("The provided test data file %s is not present in the location %s", fileName, filePath));
			exception.printStackTrace();
			throw (exception);
		}catch (IOException exception) {
			System.out.println(
					String.format("The provided test data file %s is not present in the location %s", fileName, filePath));
			exception.printStackTrace();
			throw (exception);
		}

		return excelTestData;
	}
}
