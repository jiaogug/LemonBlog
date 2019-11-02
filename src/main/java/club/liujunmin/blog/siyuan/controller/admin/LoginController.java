package club.liujunmin.blog.siyuan.controller.admin;

import club.liujunmin.blog.siyuan.entity.User;
import club.liujunmin.blog.siyuan.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session){
        User user = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user", user);
            return new ModelAndView("admin/index");
        }else {
            ModelAndView modelAndView = new ModelAndView("admin/login");
            modelAndView.addObject("message" ,"用户名或密码错误");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
