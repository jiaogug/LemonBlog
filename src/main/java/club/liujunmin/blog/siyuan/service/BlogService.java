package club.liujunmin.blog.siyuan.service;

import club.liujunmin.blog.siyuan.entity.Blog;
import club.liujunmin.blog.siyuan.entity.BlogQuery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id, Blog blog) throws NotFoundException;
    void deleteBlog(Long id);
}
