package com.khalid.recipeblog.repositories;

import com.khalid.recipeblog.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByUserId(Long id);
}
