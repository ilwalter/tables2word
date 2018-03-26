package org.overcome.core;

import java.util.List;

import org.overcome.struct.Comment;

public interface CommentReader {

	/**
	 * 解析一个字符串封装成对应的Comment信息
	 * @param commentStr
	 * @return List<Comment>
	 */
	List<Comment> read(String commentStr);
}
