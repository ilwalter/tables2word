package org.overcome.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.overcome.transform.SimpleWordTableWraper;
import org.overcome.util.DBUtil;

/**
 * 主要应用程序
 * @author Administrator
 *
 */
public class MainApplication {

	public static void main(String[] args) {
		try {
			/*
			 * 打包发布的时候，记住需要修改Configration类中config.properties文件的读取路径
			 * org.overcome.core.Configration
			 */
			
			/*生成文档*/
			System.out.println("DBDoc：生成数据库逆向文档开始！");
			System.out.println("DBDoc：等待。。。");
			XWPFDocument document = new XWPFDocument();
			SimpleWordTableWraper sww = new SimpleWordTableWraper();
			DBUtil duDbUtil = new DBUtil();
			List<Map<String,String>> tables = duDbUtil.getTables();
			sww.wraper(document, duDbUtil.initTables(tables), "列名","类型","键","可空","默认值","自增","备注");
			System.out.println("DBDoc:生成数据库逆向文档成功！");
			System.out.println("DBDoc:");
			System.out.println("	共生成"+tables.size()+"张表");
			int index = 1;
			for(Map<String,String> t:tables)
				System.out.println("	"+(index++)+"、"+t.get("name")+"-"+t.get("comment"));
			
			/*输出到文件*/
			System.out.println("DBDoc:输出到文档文件开始！");
			System.out.println("DBDoc:等待。。。。");
			String targetPath =duDbUtil.getDbm().getConfigration().getTargetPath();
			String targetFile =duDbUtil.getDbm().getConfigration().getTargetFile();
			FileOutputStream out = new FileOutputStream(targetPath+targetFile);
			document.write(out);
			System.out.println("DBDoc:输出到文档文件成功！");
			System.out.println("DBDoc:"+targetPath+targetFile);
			
			duDbUtil.getDbm().close(); //关闭数据库链接
			out.close();//关闭输出流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
