package com.khalid.recipeblog;

import com.khalid.recipeblog.entities.Blog;
import com.khalid.recipeblog.repositories.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void createTest(){
        Blog blog = new Blog();
        blog.setTitle("XYZ");
        blog.setAuthor("JOHN");
        blog.setDescription("vnvhjmfgjxdhgxfgfghgj");

        this.blogRepository.save(blog);
        Assertions.assertThat(blog.getBlogId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getBlogTest(){
        Blog blog = this.blogRepository.findById(1).get();
        Assertions.assertThat(blog.getBlogId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void getAllBlogsTest(){
        List<Blog> blogs = this.blogRepository.findAll();
        Assertions.assertThat(blogs.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void  updateTest(){
        Blog blog = this.blogRepository.findById(1).get();
        blog.setTitle("cghvhj");
        Blog blog2 = this.blogRepository.save(blog);

       Assertions.assertThat(blog2.getTitle()).isEqualTo("cghvhj");
    }
}
