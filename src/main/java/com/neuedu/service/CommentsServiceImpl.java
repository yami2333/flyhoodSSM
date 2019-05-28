package com.neuedu.service;

import com.neuedu.dao.CommentsDao;
import com.neuedu.dao.CommentsDaoImpl;
import com.neuedu.pojo.Comments;

public class CommentsServiceImpl implements CommentsService {
    private CommentsDao commentsDao = new CommentsDaoImpl();
    @Override
    public int addComment(Comments comments) {
        commentsDao.updateCommNum(comments);
        return commentsDao.addComment(comments);
    }
}
