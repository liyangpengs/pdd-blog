package com.pdd.dao;

import java.util.List;

import com.pdd.bean.comment;
import com.pdd.bean.comments;

public interface commentDao {
	int addcomment(comment comment);
	List<comment> getcomment(Integer nid);
	int addcomments(comments comments);
}
