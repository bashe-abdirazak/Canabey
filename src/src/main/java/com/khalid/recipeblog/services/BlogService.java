package com.khalid.recipeblog.services;

import com.khalid.recipeblog.entities.Blog;

import java.security.Principal;
import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();

    void create(Blog blog, String email);

    Blog getBlog(Integer blogId);

    List<Blog> getAllBlogsByUser(String email);

    void update(Blog blog, Integer blogId);
}
