package com.pdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pdd.bean.comment;
import com.pdd.bean.comments;

@Service
public interface commentService {
	int addcomment(comment comment);
	List<comment> getcomment(Integer nid);
	int addcomments(comments comments);
}
