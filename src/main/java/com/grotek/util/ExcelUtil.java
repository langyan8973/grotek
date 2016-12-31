package com.grotek.util;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class ExcelUtil {
	
	public static HSSFWorkbook exportExcel(List<List> datas,List<Integer> widths,String title,List<Integer> mergeRows){
		HSSFWorkbook workbook = null;
		try {

		workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(title);

		setSheetColumnWidth(sheet,widths);

		HSSFCellStyle style = createTitleStyle(workbook);

		if (datas != null && datas.size() > 0) {
			
			for (int i = 0; i < datas.size(); i++) {
				List values = datas.get(i);
				if(values!=null && values.size()>0){
					HSSFRow row = sheet.createRow((short) i);
					for (int j = 0; j < values.size(); j++) {
						createCell(row, j, style, HSSFCell.CELL_TYPE_STRING, values.get(j)==null?"":values.get(j).toString());
					}
				}				
			}
			if(mergeRows!=null && mergeRows.size()>0){
				int start=1;
				for (Iterator iterator = mergeRows.iterator(); iterator.hasNext();) {
					Integer size = (Integer) iterator.next();
					mergeCell(sheet, start, start+size-1, 0, 0);
					start=start+size;
				}
			}

		} else {

			createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "空");

		}

		} catch (Exception e) {

		e.printStackTrace();

		}

		return workbook;
	}
	
	public static void setSheetColumnWidth(HSSFSheet sheet,List<Integer> widths) {
		
		for (int i = 0; i < widths.size(); i++) {
			sheet.setColumnWidth(i, widths.get(i));
		}

	}
	
	public static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {

		HSSFFont boldFont = wb.createFont();
	
		boldFont.setFontHeight((short) 200);
	
		HSSFCellStyle style = wb.createCellStyle();
	
		style.setFont(boldFont);
	
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		
		style.setWrapText(true);
	
		return style;

	}
	
	public static void createCell(HSSFRow row, int column, HSSFCellStyle style,

			int cellType, Object value) {

		HSSFCell cell = row.createCell(column);
	
		if (style != null) {
	
			cell.setCellStyle(style);
	
		}
	
		switch (cellType) {
	
		case HSSFCell.CELL_TYPE_BLANK: {
	
		}
	
			break;
	
		case HSSFCell.CELL_TYPE_STRING: {
	
			cell.setCellValue(value.toString());
	
		}
	
			break;
	
		case HSSFCell.CELL_TYPE_NUMERIC: {
	
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		
			cell.setCellValue(Double.parseDouble(value.toString()));
	
		}
	
			break;
	
		default:
	
			break;
	
		}

	}
	
	/**
	 * 合并单元格
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
     * @param lastCol
     */
	@SuppressWarnings("deprecation")
	public static void mergeCell(HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new Region(firstRow,(short)firstCol,lastRow,(short)lastCol));
	}

}
