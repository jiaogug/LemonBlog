package club.liujunmin.blog.siyuan.controller.admin;

import club.liujunmin.blog.siyuan.entity.Tag;
import club.liujunmin.blog.siyuan.entity.Type;
import club.liujunmin.blog.siyuan.service.TagService;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public ModelAndView list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                             Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("admin/tags");
        modelAndView.addObject("page",tagService.listTag(pageable));
        return modelAndView;
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTag(@Valid Tag tag, RedirectAttributes attributes){
        Tag t = tagService.saveTag(tag);
        if (t == null){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public ModelAndView editInput(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/tags-edit");
        modelAndView.addObject("tag", tagService.getTag(id));
        return modelAndView;
    }

    @PostMapping("/tags/{id}")
    public String editTag(@Valid Tag tag, @PathVariable Long id, RedirectAttributes attributes){
        Tag t = null;
        try {
            t = tagService.updateTag(id, tag);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (t == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
