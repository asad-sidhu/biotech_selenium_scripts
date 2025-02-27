package MiscFunctions;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CompareExcelFiles {


	public void verifyIfExcelFilesHaveSameNumberAndNameOfSheets(Workbook workbook1, Workbook workbook2) {
		System.out.println("Verifying if both work books have same number of sheets.............");
		int sheetsInWorkbook1 = workbook1.getNumberOfSheets();
		int sheetsInWorkbook2 = workbook2.getNumberOfSheets();
		Assert.assertEquals(sheetsInWorkbook1, sheetsInWorkbook2,
				"Excel work books have different number of sheets. \n "
						+ "Sheets in work book 1 : "+sheetsInWorkbook1 +"\n "
						+ "Number of sheets in work book 2 : "+sheetsInWorkbook2);
		System.out.println("Sheets in first work book : "+sheetsInWorkbook1);
		System.out.println("Sheets in second work book : "+sheetsInWorkbook2);
		System.out.println("Both work books have same number of sheets.........................");

		System.out.println("Verifying if both work books have same name of sheets.............");
		List<String> sheetsNameOfWb1 = new ArrayList<>();
		List<String> sheetsNameOfWb2 = new ArrayList<>();
		// Since we have already verified that both work books have same number of sheets so iteration can be done against any workbook sheet count
		for (int i = 0; i < sheetsInWorkbook1; i++) {
			// Retrieving sheet names from both work books and adding to different lists
			sheetsNameOfWb1.add(workbook1.getSheetName(i));
			sheetsNameOfWb2.add(workbook2.getSheetName(i));
		}
		// Since I am relaxing same sequence of sheets. 
		Collections.sort(sheetsNameOfWb1);
		Collections.sort(sheetsNameOfWb2);
		Assert.assertEquals(sheetsNameOfWb1, sheetsNameOfWb2, "Provided excel work books have different name of sheets.");
		System.out.println("Sheet Names in first work book : "+sheetsNameOfWb1);
		System.out.println("Sheet Names in second work book : "+sheetsNameOfWb2);
		System.out.println("Both work books have same name of sheets.........................");
	}

	// This method compares if both excel files have same number of rows and corresponding columns
	public void verifySheetsInExcelFilesHaveSameRowsAndColumns(Workbook workbook1, Workbook workbook2) {
		System.out.println(
				"Verifying if both work books have same number of rows and columns in all sheets.............");
		int sheetCounts = workbook1.getNumberOfSheets();
		for (int i = 0; i < sheetCounts; i++) {
			Sheet s1 = workbook1.getSheetAt(i);
			Sheet s2 = workbook2.getSheetAt(i);
			int rowsInSheet1 = s1.getPhysicalNumberOfRows();
			int rowsInSheet2 = s2.getPhysicalNumberOfRows();
			Assert.assertEquals(rowsInSheet1, rowsInSheet2, "Sheets have different count of rows..");

			Iterator<Row> rowInSheet1 = s1.rowIterator();
			Iterator<Row> rowInSheet2 = s2.rowIterator();
			while (rowInSheet1.hasNext()) {
				int cellCounts1 = rowInSheet1.next().getPhysicalNumberOfCells();
				int cellCounts2 = rowInSheet2.next().getPhysicalNumberOfCells();
				Assert.assertEquals(cellCounts1, cellCounts2, "Sheets have different count of columns..");
			}
		}
	}


	public void verifyDataInExcelBookAllSheets(Workbook workbook1, Workbook workbook2) {
		System.out.println("Verifying if both work books have same data.............");
		SoftAssert softAssert = new SoftAssert();
		int sheetCounts = workbook1.getNumberOfSheets();

		for (int i = 0; i < sheetCounts; i++) {
			// Get sheet at same index of both work books
			Sheet s1 = workbook1.getSheetAt(i);
			Sheet s2 = workbook2.getSheetAt(i);
			System.out.println("*********** Sheet Name : "+s1.getSheetName()+"*************");
			// Iterating through each row
			int rowCounts = s1.getPhysicalNumberOfRows();
			for (int j = 0; j < rowCounts; j++) {
				// Iterating through each cell
				int cellCounts = s1.getRow(j).getPhysicalNumberOfCells();
				for (int k = 0; k < cellCounts; k++) {
					// Getting individual cell
					Cell c1 = s1.getRow(j).getCell(k);
					Cell c2 = s2.getRow(j).getCell(k);
					// Since cell have types and need o use different methods

					try {
						if (c1.getCellType().equals(c2.getCellType())) {										
							if (c1.getCellType() == CellType.STRING) {
								String v1 = c1.getStringCellValue();
								String v2 = c2.getStringCellValue();
								softAssert.assertEquals(v1, v2, "Cell values are different.....");
						//		System.out.println("Its matched : "+ v1 + " === "+ v2);
							}
							if (c1.getCellType() == CellType.NUMERIC) {
								// If cell type is numeric, we need to check if data is of Date type
								if (DateUtil.isCellDateFormatted(c1) | DateUtil.isCellDateFormatted(c2)) {
									// Need to use DataFormatter to get data in given style otherwise it will come as time stamp
									DataFormatter df = new DataFormatter();
									String v1 = df.formatCellValue(c1);
									String v2 = df.formatCellValue(c2);
									softAssert.assertEquals(v1, v2, "Cell values are different.....");
							//		System.out.println("Its matched : "+ v1 + " === "+ v2);
								} else {
									double v1 = c1.getNumericCellValue();
									double v2 = c2.getNumericCellValue();
									softAssert.assertEquals(v1, v2, "Cell values are different.....");
								//	System.out.println("Its matched : "+ v1 + " === "+ v2);
								}
							}
							if (c1.getCellType() == CellType.BOOLEAN) {
								boolean v1 = c1.getBooleanCellValue();
								boolean v2 = c2.getBooleanCellValue();
								softAssert.assertEquals(v1, v2, "Cell values are different.....");
								System.out.println("Its matched : "+ v1 + " === "+ v2);
							}
						} else
						{
							// If cell types are not same, exit comparison  
							softAssert.fail("Non matching cell type.");
						}
					}
					catch(NullPointerException ex) {
						System.out.println("Null column skipped");
						continue;
					}
				}
			}
		}
		softAssert.assertAll();
		System.out.println("Hurray! Both work books have same data.");
	}


	@Test
	public void sameContentOfExcelFiles() throws EncryptedDocumentException, IOException {
		String userDir = System.getProperty("user.dir");
		//Workbook wb1 = WorkbookFactory.create(new File(userDir+"\\src\\test\\resources\\AssetResultsMatch\\AssetDataPreDeployment.xlsx"));
		//Workbook wb2 = WorkbookFactory.create(new File(userDir+"\\src\\test\\resources\\AssetResultsMatch\\AssetDataPostDeployment.xlsx"));
		Workbook wb1 = WorkbookFactory.create(new File(userDir+"\\src\\test\\resources\\AssetResultsMatch\\a.xlsx"));
		Workbook wb2 = WorkbookFactory.create(new File(userDir+"\\src\\test\\resources\\AssetResultsMatch\\b.xlsx"));

		CompareExcelFiles mse_CompareExcelFiles = new CompareExcelFiles();
		mse_CompareExcelFiles.verifyIfExcelFilesHaveSameNumberAndNameOfSheets(wb1, wb2);
		mse_CompareExcelFiles.verifySheetsInExcelFilesHaveSameRowsAndColumns(wb1, wb2);
		mse_CompareExcelFiles.verifyDataInExcelBookAllSheets(wb1, wb2);

	}
}
