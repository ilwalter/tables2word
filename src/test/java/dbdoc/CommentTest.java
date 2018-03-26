package dbdoc;

import java.util.List;

import org.junit.Test;
import org.overcome.core.CommentReader;
import org.overcome.core.SimpleCommentReader;
import org.overcome.struct.Comment;

public class CommentTest {
	
	@Test
	public void testComment() throws Exception {
		String str = "@name     班级\r\n@type base\r\n\n\n@desciption 用于存储学生班级信息\r\n@version v1.0\r\n@updated 修改了某个字段\r\n\r\n修改了某个字段\r\n\r\n";
		str = "班级";
		CommentReader reader = new SimpleCommentReader();
		List<Comment> cs = reader.read(str);
		for(Comment c :cs)
			System.out.println("syn:"+c.getSyntax()+" text:"+c.getText());
	}

}
