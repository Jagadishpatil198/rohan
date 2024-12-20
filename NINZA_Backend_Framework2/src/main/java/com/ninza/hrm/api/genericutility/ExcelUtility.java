package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * @author RAJU
 */
public class ExcelUtility implements IPathConstants {

	/**
	 * @author RAJU
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 */
	public String getDataFromExcelSheet(String sheetName, int rowNo, int cellNo) {
		Workbook wb = null;
		String data=null;
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			wb = WorkbookFactory.create(fis);
			data = wb.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
		
	}
	/**
	 * @author RAJU
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @return
	 * @throws Throwable
	 */

	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		return data;
	}
	/**
	 * @author RAJU
	 * @param sheetName
	 * @param l
	 */
	public void writeBackDataToExcel(String sheetName, ArrayList<String[]> l) {

		Workbook wb = null;
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sh = wb.getSheet(sheetName);
		int rowNo = 0;
		for (String[] rowData : l) {
			Row row = sh.getRow(rowNo++);
			int cellNo = 0;
			for (String cellDate : rowData) {
				Cell cell = row.getCell(cellNo++);
				cell.setCellValue(cellDate);

			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(excelPath);
			wb.write(fos);
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author RAJU
	 * @param sheetName
	 * @return
	 */
	public int getRowCountFromExcel(String sheetName) {
		FileInputStream fis;
		Workbook wb = null;
		try {
			fis = new FileInputStream(excelPath);
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int lastRowCount = wb.getSheet(sheetName).getLastRowNum();
		return lastRowCount;

	}

	/**
	 * @author RAJU
	 */
	public void closeExcel() {
		FileInputStream fis;
		Workbook wb = null;
		try {
			fis = new FileInputStream(excelPath);
			wb = WorkbookFactory.create(fis);
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
