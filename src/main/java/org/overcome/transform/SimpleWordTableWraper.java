package org.overcome.transform;

import java.util.List;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.overcome.struct.Column;
import org.overcome.struct.Table;
import org.overcome.util.WordTableUtil;

public class SimpleWordTableWraper implements WordTableWraper{
	
	@Override
	public void wraper(XWPFDocument doc,List<Table> tables, String... titles) {
		
		//第一部分标题
		title(doc, "一、数据表一览", true, 18);
		
		 //数据库表信息总述段落	
		XWPFParagraph dbinfo = doc.createParagraph();
		XWPFRun dbinfor = dbinfo.createRun();
		dbinfor.setText("数据表总数："+tables.size());
		dbinfor.addBreak();
		
		/*数据库表表格-创建*/
		XWPFTable tableDir = WordTableUtil.createTable(doc, tables.size()+1, 2, "表名","备注");
		WordTableUtil.setColumnsWidth(tableDir, 3000,6000);
		WordTableUtil.setAlign(tableDir, STJc.CENTER);
		
		/*数据库表表格-数据生成*/
		for(int i = 0;i < tables.size();i++){
			setTableDir(tables.get(i), tableDir.getRow(i+1));
		}
		doc.createParagraph().createRun().addBreak(BreakType.PAGE);
		
		
		//第二部分标题
		title(doc, "二、数据表列表", true, 18);
		
		/*数据表与字段生成*/
		int n = 1;
		for(Table t :tables){
			XWPFParagraph p = doc.createParagraph();
			XWPFRun r = p.createRun();
			r.setText(n+"、 "+t.getName()+"（总列数："+t.getColumns().size()+"）");
			r.addBreak();
			r.setText("描述："+t.getDescription());
			
			XWPFTable table = WordTableUtil.createTable(doc, t.getColumns().size()+1, 7, "列名","类型","键","可空","默认","自增","备注");
			WordTableUtil.setColumnsWidth(table, 1500,1500,800,800,800,800,3000);
			WordTableUtil.setAlign(table, STJc.CENTER);
			doc.createParagraph();
			
			/*为列添加数据*/
			for(int i = 0;i < t.getColumns().size();i++)
				setWordTableCellText( t.getColumns().get(i),table.getRow(i+1));
			n++;
		}
		
	}
	
	/**
	 * 填充数据字段表列
	 * @param c
	 * @param row
	 */
	private void setWordTableCellText(Column c,XWPFTableRow row){
		setCellPragraphText(row, 0, c.getName());
		setCellPragraphText(row, 1, c.getType());
		setCellPragraphText(row, 2, c.getKey());
		setCellPragraphText(row, 3, c.getIsNull()?"是":"否");
		setCellPragraphText(row, 4, c.getDefaultValue()==""?"''":c.getDefaultValue());
		setCellPragraphText(row, 5, c.getExtra().isEmpty()?"否":"是");
		setCellPragraphText(row, 6, c.getDescription());
	}
	
	/**
	 * 填充一览表的列
	 * @param t
	 * @param row
	 */
	private void setTableDir(Table t,XWPFTableRow row){
		setCellPragraphText(row, 0, t.getName());
		setCellPragraphText(row, 1, t.getDescription());
	}
	
	
	/**
	 * 设置wordCell内容
	 * @param row
	 * @param index
	 * @param text
	 */
	private void setCellPragraphText(XWPFTableRow row,int index,String text){
		XWPFParagraph p = row.getCell(index).getParagraphs().get(0);
		p.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r = p.createRun();
		r.setText(text);
	}
	
	
	/**
	 * 文档标题
	 * @param doc
	 * @param title
	 * @param bold
	 * @param fontSize
	 */
	private void title(XWPFDocument doc,String title,boolean bold,int fontSize){
		XWPFParagraph dbinfoTitle = doc.createParagraph();
		XWPFRun dbinforTitle = dbinfoTitle.createRun();
		dbinforTitle.setBold(bold);
		dbinforTitle.setFontSize(fontSize);
		dbinforTitle.setText(title);
	}

}
