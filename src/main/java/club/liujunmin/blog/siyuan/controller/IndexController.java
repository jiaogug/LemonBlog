package club.liujunmin.blog.siyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        System.out.println("------index------");
        return "index";
    }

    @GetMapping("/index")
    public String hello(){
        return "index";
    }
}
