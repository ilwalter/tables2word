package org.overcome.transform;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.overcome.struct.Table;

public interface WordTableWraper {
	/**
	 *  从数据转换成目标目标表格
	 * @param doc
	 * @param tables
	 * @param titles
	 */
	void wraper(XWPFDocument doc,List<Table> tables,String ... titles);

}
