package club.liujunmin.blog.siyuan.controller;

import club.liujunmin.blog.siyuan.entity.BlogQuery;
import club.liujunmin.blog.siyuan.service.BlogService;
import club.liujunmin.blog.siyuan.service.TagService;
import club.liujunmin.blog.siyuan.service.TypeService;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;

    @GetMapping({"/", "/index"})
    public String index(@PageableDefault(size = 8, direction = Sort.Direction.DESC, sort = {"updateTime"}) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(6));
        return "index";
    }

    @PostMapping("/search")
    public String research(@PageableDefault(size = 8, direction = Sort.Direction.DESC, sort = {"updateTime"}) Pageable pageable,
                           @RequestParam String query, Model model){
        model.addAttribute("page", blogService.listBlog(pageable, "%" + query + "%"));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) throws NotFoundException {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}
