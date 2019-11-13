package club.liujunmin.blog.siyuan.service;

import club.liujunmin.blog.siyuan.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
