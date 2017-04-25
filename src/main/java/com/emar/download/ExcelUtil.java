package com.emar.download;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.emar.download.domain.ExcelHeader;
import com.emar.exception.BussinessException;

/**
 * @author franplk
 *  2016-01-11
 */
public class ExcelUtil {
	
	/**
	 * Export Excel
	 */
	public static void exportCompExcel(List<ExcelHeader> headers, List<Map<String, Object>> dataset, OutputStream out, String title) {
		if (headers == null) {
			throw new BussinessException("[Excel]没有表头");
		}
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(15);
		// 数据的查询条件加入
		int rowIndex = 0;
		// 生成表头样式
		HSSFCellStyle style_head = getcellStyle(workbook, 0);
		// 产生表格标题行(有复合行，生成两行)
		HSSFRow row_head = sheet.createRow(rowIndex);
		HSSFRow sub_head = sheet.createRow(rowIndex+1);;
		int colIdx_head = 0;
		for (ExcelHeader head : headers) {
			List<ExcelHeader> subHeaders = head.getSubHeader();
			if(subHeaders != null){
				int subsize = subHeaders.size();
				setCell(row_head, colIdx_head, head.getTitle(), style_head);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, colIdx_head, subsize + colIdx_head - 1));
				for(ExcelHeader subhd : subHeaders){
					setCell(sub_head, colIdx_head++, subhd.getTitle(), style_head);
				}
			} else {
				setCell(row_head, colIdx_head, head.getTitle(), style_head);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + 1, colIdx_head, colIdx_head++));
			}
		}
		// 设置Excel内容样式
		HSSFCellStyle style_row = getcellStyle(workbook, 1);
		rowIndex += 2;
		// 遍历集合数据，产生数据行
		for(Map<String, Object> rowdata : dataset) {
			HSSFRow row_normal = sheet.createRow(rowIndex);
			int colIdx_con = 0;
			for(ExcelHeader head : headers){
				List<ExcelHeader> subHeaders = head.getSubHeader();
				if(subHeaders != null){
					for(ExcelHeader subhd : subHeaders){
						String headtitle = subhd.getField();
						Object value = rowdata.get(headtitle);
						setCell(row_normal, colIdx_con, value, style_row);
						colIdx_con++;
					}
				} else {
					String headtitle = head.getField();
					Object value = rowdata.get(headtitle);
					setCell(row_normal, colIdx_con, value, style_row);
					colIdx_con++;
				}
			}
			rowIndex++;
		}
		writeTo(workbook, out);
	}
	
	/**
	 * Get Excel Style
	 */
	private static HSSFCellStyle getcellStyle(HSSFWorkbook workbook, int type) {
		HSSFCellStyle style = workbook.createCellStyle();
		if(type == 0){ // head
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font_head = workbook.createFont();
			font_head.setFontHeightInPoints((short) 12);
			font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font_head);
		} else if(type == 1) {// normal
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont font_row = workbook.createFont();
			font_row.setFontHeightInPoints((short) 10);
			font_row.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			
			HSSFDataFormat format = workbook.createDataFormat();  
			style.setDataFormat(format.getFormat("0.00"));  
			
			// 把字体应用到当前的样式
			style.setFont(font_row);
		}
		return style;
	}
	
	/**
	 * Set Cell Value
	 */
	private static void setCell(HSSFRow row, int index, Object value, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(index);
		if(style != null){
			cell.setCellStyle(style);
		}
		if(value == null) {
			value = "";
		}
		HSSFRichTextString text = new HSSFRichTextString(String.valueOf(value));
		cell.setCellValue(text);
	}
	
	/**
	 * Write To WookBook
	 */
	private static void writeTo(HSSFWorkbook workbook, OutputStream out) {
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}