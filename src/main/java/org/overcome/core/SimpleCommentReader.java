package org.overcome.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.overcome.struct.Comment;
import org.overcome.struct.Comment.Syntax;

public class SimpleCommentReader implements CommentReader{

	@Override
	public List<Comment> read(String commentStr) {
		List<Comment> comments = new ArrayList<Comment>();
		if(commentStr == null || commentStr.equals(""))
			return comments;
		
		/*分割字符串*/
		String[] commentStrAryay = commentStr.split("\n"); 
		/*清洗字符串数组*/
		List<String> commentStrList = clear(commentStrAryay);
		/*分析封装@注释属性*/
		return analyse(commentStrList);
	}
	
	
	private List<String> clear(String[] commentStrAryay){
		List<String> strs = new ArrayList<String>();
		/*清洗空串*/
		for (int i = 0; i < commentStrAryay.length; i++) {
			String s = commentStrAryay[i];
			if(s.trim().isEmpty() && !s.equals("") )
				continue;
			
			/*字符串追加*/
			if(s.contains("@"))
				strs.add(s);
			else{
				int last = strs.size()-1;
				if(last>0){
					String lastStr = strs.get(last);
					strs.set(last, lastStr+s);	
				}
				
			}//else
		}//for
		
		return strs;
	}
	
	private List<Comment> analyse(List<String> commentStrList){
		List<Comment> comments = new ArrayList<Comment>();
		for(String c : commentStrList){
			
			Comment cm = null;
			Pattern p = Pattern.compile("@([a-z]+)\\s+(\\S+)");
			Matcher matcher = p.matcher(c);
			while(matcher.find()){
				cm =warpComment(matcher.group(1), matcher.group(2));
			}
			if(cm != null)
				comments.add(cm);
		}
		
		return comments;
	}
	
	
	private Comment warpComment(String syntax,String text){
		Syntax s = null;
		try {
			 s = Syntax.valueOf(syntax);
		} catch (Exception e) {
			s = null;
		}
		
		if(s==null)
			return null;
		
		Comment c = null;
		switch (s) {
		case name:
			c = new Comment();
			c.setSyntax(Syntax.name);
			c.setText(text.trim());
			break;
		case type:
			c = new Comment();
			c.setSyntax(Syntax.type);
			c.setText(text.trim());
			break;
		case description:
			c = new Comment();
			c.setSyntax(Syntax.description);
			c.setText(text.trim());
			break;
		case version:
			c = new Comment();
			c.setSyntax(Syntax.version);
			c.setText(text.trim());
			break;
		case updated:
			c = new Comment();
			c.setSyntax(Syntax.updated);
			c.setText(text.trim());
			break;
		default:
			c = null;
			break;
		}
		return c;
	}
}
