package com.khalid.recipeblog.services;

import com.khalid.recipeblog.entities.Blog;
import com.khalid.recipeblog.entities.User;
import com.khalid.recipeblog.repositories.BlogRepository;
import com.khalid.recipeblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> getAllBlogs() {
        return this.blogRepository.findAll();
    }

    @Override
    public void create(Blog blog, String email) {
      User user =  this.userRepository.findByEmail(email);
      blog.setUser(user);
      this.blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(Integer blogId) {
        return this.blogRepository.findById(blogId).get();
    }

    @Override
    public List<Blog> getAllBlogsByUser(String email) {
        User user = this.userRepository.findByEmail(email);
        return this.blogRepository.findByUserId(user.getId());
    }

    @Override
    public void update(Blog blog, Integer blogId) {
     Blog blog2 = this.blogRepository.getById(blogId);
     User user = blog2.getUser();
     blog.setBlogId(blog2.getBlogId());
     blog.setUser(user);
     this.blogRepository.save(blog);
    }
}
