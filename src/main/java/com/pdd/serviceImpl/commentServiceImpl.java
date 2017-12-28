package com.pdd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.comment;
import com.pdd.bean.comments;
import com.pdd.dao.commentDao;
import com.pdd.service.commentService;

@Service
public class commentServiceImpl implements commentService{
	
	@Autowired
	private commentDao cdao;
	
	@Override
	public int addcomment(comment comment) {
		return cdao.addcomment(comment);
	}

	@Override
	public List<comment> getcomment(Integer nid) {
		return cdao.getcomment(nid);
	}

	@Override
	public int addcomments(comments comments) {
		return cdao.addcomments(comments);
	}
}
