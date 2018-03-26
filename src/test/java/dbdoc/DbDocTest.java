package dbdoc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.overcome.struct.Column;
import org.overcome.struct.Table;
import org.overcome.transform.SimpleWordTableWraper;
import org.overcome.util.DBUtil;

public class DbDocTest {
	
	@Test
	public void testGetDbStructs() throws Exception {
		
		DBUtil tu = new DBUtil();
		List<Table> tables = tu.initTables(tu.getTables());
		for(Table t : tables){
			System.out.println("表："+t.getName()+"  描述："+t.getDescription());
			
			for(Column c : t.getColumns()){
				System.out.println(c.getName()+" "+c.getType()+"  "+c.getDescription());
			}
			System.out.println();

		}
	}
	
	@Test
	public void testCreateWord(){	
		
		try {
			FileOutputStream out = new FileOutputStream("D://test.doc");
			XWPFDocument doc = new XWPFDocument();

			SimpleWordTableWraper sww = new SimpleWordTableWraper();
			DBUtil tu = new DBUtil();
			sww.wraper(doc, tu.initTables(tu.getTables()), "列名","类型","键","可空","默认值","自增","备注");
			
			doc.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
