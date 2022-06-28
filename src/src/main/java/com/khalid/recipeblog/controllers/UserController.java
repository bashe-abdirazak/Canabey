package com.khalid.recipeblog.controllers;

import com.khalid.recipeblog.entities.Blog;
import com.khalid.recipeblog.entities.User;
import com.khalid.recipeblog.repositories.BlogRepository;
import com.khalid.recipeblog.repositories.UserRepository;
import com.khalid.recipeblog.services.BlogServiceImpl;
import com.khalid.recipeblog.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogServiceImpl blogServiceImpl;

	@Autowired
	private BlogRepository blogRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		String email = principal.getName();
		UserDetails details = userServiceImpl.loadUserByUsername(email);
		if (details != null && details.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			List<Blog> blogs = this.blogServiceImpl.getAllBlogs();
			model.addAttribute("blogs", blogs);
			return "redirect:/admin";

		}
		return "redirect:/home";
	}
	@GetMapping("/admin")
	public String adminHomePage(Model model, Principal principal) {
		String email =principal.getName();
		List<Blog> blogs = this.blogServiceImpl.getAllBlogs();
		List<Blog> blogs2 = this.blogServiceImpl.getAllBlogsByUser(email);
		model.addAttribute("blogs", blogs);
		model.addAttribute("blogs2", blogs2);
		return "admin";
	}

	@GetMapping("/home")
	public String normalHomePage(Model model) {
		List<Blog> blogs = this.blogServiceImpl.getAllBlogs();
		model.addAttribute("blogs", blogs);
		return "home";
	}

	@GetMapping("/addBlogForm")
	public String addBlogForm() {
		return "addBlog";
	}

	@GetMapping("/editBlogForm/{blogId}")
	public String editBlogForm(Model model, @PathVariable Integer blogId) {
		model.addAttribute("blogId", blogId);
		return "editBlog";
	}

	@ModelAttribute("blog")
	public Blog blog() {
		return new Blog();
	}

	@PostMapping("/addBlog")
	public String addBlog(@ModelAttribute("blog") Blog blog, Principal principal) {
		String email = principal.getName();
		this.blogServiceImpl.create(blog, email);
		return "redirect:/admin";
	}


	@PostMapping("/editBlog/{blogId}")
	public String editBlog(@ModelAttribute("blog") Blog blog, @PathVariable Integer blogId) {
		this.blogServiceImpl.update(blog, blogId);
		return "redirect:/admin";
	}

	@GetMapping("/home/recipe/{blogId}")
	public String recipe(Model model, @PathVariable Integer blogId) {
		Blog blog = this.blogServiceImpl.getBlog(blogId);
		model.addAttribute("blog", blog);
		return "recipe";
	}
}
