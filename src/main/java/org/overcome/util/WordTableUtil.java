package org.overcome.util;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc.Enum;

public class WordTableUtil {

	/**
	 * 创建数据库表
	 * @param doc
	 * @param rows
	 * @param columns
	 * @param titles
	 * @return
	 */
	public static XWPFTable createTable(XWPFDocument doc ,int rows,int columns,String ... titles){
		XWPFTable table = doc.createTable(rows, columns);
		setHeaderTitle(table, titles);
		return table;
	}
	
	
	/**
	 * 首行表头设置
	 * @param table
	 * @param titles
	 */
	public static  void setHeaderTitle(XWPFTable table,String ... titles){
		XWPFTableRow row = table.getRow(0);
		for(int i = 0;i< titles.length; i++){
			row.getCell(i).setColor("9CC2FB");
			XWPFParagraph p = row.getCell(i).getParagraphs().get(0);
			p.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r =p.createRun();
			r.setText(titles[i]);
		}
	}
	
	
	/**
	 * 表格排版位置
	 * @param table
	 * @param align
	 */
	public static void setAlign(XWPFTable table,Enum align){
		CTTbl ttbl = table.getCTTbl();
		ttbl.addNewTblPr().addNewJc().setVal(align);
	}
	

	/**
	 * 设置列宽
	 * @param table
	 * @param widths
	 */
	public static void setColumnsWidth(XWPFTable table, int... widths) {
		CTTbl ttbl = table.getCTTbl();
		CTTblGrid tblGrid = ttbl.getTblGrid() != null ? ttbl.getTblGrid() : ttbl.addNewTblGrid();
		for(int w : widths){
			CTTblGridCol gridCol = tblGrid.addNewGridCol();
			gridCol.setW(new BigInteger(String.valueOf(w)));
		}
	}
	
	
	/**
	 * 设置行高
	 * @param table
	 * @param heights
	 */
	public static void seRowHeight(XWPFTable table, int... heights) {
		for(int i = 0; i < heights.length; i++ )
			table.getRow(i).setHeight(heights[i]);
	}

}
