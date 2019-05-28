package com.neuedu.mapper;

import com.neuedu.pojo.Comments;

public interface CommentsMapper {
    // 添加评论
    int addComment(Comments comments);
    // 修改评论量
    void updateCommNum(Comments comments);
}
