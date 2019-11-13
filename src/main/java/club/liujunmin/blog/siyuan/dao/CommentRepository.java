package club.liujunmin.blog.siyuan.dao;

import club.liujunmin.blog.siyuan.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //Sort对Comment按时间排序
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
