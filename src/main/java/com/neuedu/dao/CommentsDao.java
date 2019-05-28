package com.neuedu.dao;

import com.neuedu.pojo.Comments;

public interface CommentsDao {
    // 添加评论
    int addComment(Comments comments);
    // 修改评论量
    void updateCommNum(Comments comments);
}
