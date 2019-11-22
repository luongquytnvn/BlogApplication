package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/")
    public ModelAndView home() {
        List<Blog> blog = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("blog/home");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public String creat(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create-blog")
    public String save(Model model, Blog blog) {
        blogService.save(blog);
        model.addAttribute("blog", new Blog());
        model.addAttribute("message", "New blog created successfully");
        return "blog/create";
    }

    @GetMapping("/edit-blog/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            model.addAttribute("blog", blog);
            return "blog/edit";
        } else {
            return "blog/error404";
        }
    }

    @PostMapping("/edit-blog")
    public String updateEdit(Blog blog, Model model) {
        blogService.save(blog);
        model.addAttribute("blog", new Blog());
        model.addAttribute("message", "Blog is updated ");
        return "blog/edit";
    }

    @GetMapping("/delete-blog/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/delete";
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/";
    }

    @PostMapping("/search-blog")
    public String searchBlog(Model model, String search) {
        List<Blog> blogs = blogService.findAll();
        List<Blog> result = new ArrayList<>();
        for (Blog blog : blogs) {
            if ((blog.getTitle().contains(search))) {
                result.add(blog);
            }
        }
        model.addAttribute("blog",result);
        return "blog/home";
    }
}
