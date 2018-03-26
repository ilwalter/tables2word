package org.overcome.struct;

/**
 * 表注释
 * @author Administrator
 */
public class Comment {
	
	/**
	 * 注释类型
	 * @author Administrator
	 */
	public enum Syntax{
		name,type,description,version,updated
	}
	
	private Syntax syntax;
	private String text;
	
	public Syntax getSyntax() {
		return syntax;
	}
	public void setSyntax(Syntax syntax) {
		this.syntax = syntax;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
