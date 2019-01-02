package com.automation.framework.poc.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ReadExcelValue {

	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "Login";
		String rowId = "ValidLogin";
		String columnId = "password";
		String data = null;
		HashMap<String, String> rowIDMap, colNameMap = null;
		String rowIDValue, colHeaderName, rowCellValue, colCellValue = null;
		int intFirstCol, intLastCol = 0;
		File file = new File(System.getProperty("user.dir") + "/dataSheet/InputData.xls");
		fileInputStream = new FileInputStream(file);
		workbook = new HSSFWorkbook(fileInputStream);
		System.out.println(workbook);
		sheet = workbook.getSheet(sheetName);
		
		
		int intSheetPhyTotalRows = sheet.getPhysicalNumberOfRows();
		System.out.println(intSheetPhyTotalRows);

		rowIDMap = new HashMap<String, String>();
		for (int idRowCnt = 1; idRowCnt <= intSheetPhyTotalRows - 1; idRowCnt++) {
			row = sheet.getRow(idRowCnt);
			if (row != null) {
				cell = row.getCell(0);
			}
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				cell.setCellType(CellType.STRING);
				rowIDValue = cell.getStringCellValue();
				rowIDMap.put(rowIDValue.toUpperCase(), Integer.toString(idRowCnt));
			}
		}
		System.out.println(rowIDMap);

		colNameMap = new HashMap<String, String>();
		row = sheet.getRow(0);
		intFirstCol = row.getFirstCellNum();
		intLastCol = row.getLastCellNum();
		for (int colHeaderCnt = intFirstCol; colHeaderCnt <= intLastCol - 1; colHeaderCnt++) {
			cell = row.getCell(colHeaderCnt);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				cell.setCellType(CellType.STRING);
				colHeaderName = cell.getStringCellValue().trim();
				if (org.apache.commons.lang.StringUtils.isNotEmpty(colHeaderName)
						&& org.apache.commons.lang.StringUtils.isNotBlank(colHeaderName)) {
					colNameMap.put(colHeaderName.toUpperCase(), Integer.toString(colHeaderCnt));
				}
			}
		}
		System.out.println(colNameMap);

		rowCellValue = rowIDMap.get(rowId.toUpperCase().trim());
		System.out.println("rowCellValue::" + rowCellValue);
		colCellValue = colNameMap.get(columnId.toUpperCase().trim());
		System.out.println("colCellValue::" + colCellValue);
		row = sheet.getRow(Integer.parseInt(rowCellValue));
		if (row != null) {
			cell = row.getCell(0);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				cell = row.getCell(Integer.parseInt(colCellValue));
				cell.setCellType(CellType.STRING);
				data = cell.getStringCellValue().trim();
			}
		}

		if (org.apache.commons.lang.StringUtils.isEmpty(data) || org.apache.commons.lang.StringUtils.isBlank(data)) {
			data = null;
		}
		
		
		System.out.println("Data::" + data);
	}
}
