package club.liujunmin.blog.siyuan.controller.admin;

import club.liujunmin.blog.siyuan.entity.Type;
import club.liujunmin.blog.siyuan.service.TypeService;
import javassist.NotFoundException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("/types")
    public ModelAndView list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("admin/types");
        modelAndView.addObject("page", typeService.listType(pageable));
        return modelAndView;
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(@Valid Type type,RedirectAttributes attributes){
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message", "新增失败");
        }else{
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editType(@Valid Type type, @PathVariable Long id, RedirectAttributes attributes){
        Type t = null;
        try {
            t = typeService.updateType(id, type);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (t == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public ModelAndView editInput(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/types-edit");
        modelAndView.addObject("type", typeService.getType(id));
        return modelAndView;
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
