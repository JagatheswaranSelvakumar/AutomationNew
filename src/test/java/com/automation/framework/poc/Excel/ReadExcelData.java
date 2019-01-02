package com.automation.framework.poc.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcelData {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "Login";
		File file = new File(System.getProperty("user.dir") + "/dataSheet/InputData.xls");
		fileInputStream = new FileInputStream(file);
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());
		row = sheet.getRow(1);
		cell = row.getCell(0);
		System.out.println(cell.getStringCellValue());
		row = sheet.getRow(1);
		cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		row = sheet.getRow(2);
		cell = row.getCell(2);
		System.out.println(cell.getStringCellValue());

	}

}
