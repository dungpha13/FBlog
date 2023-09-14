package com.blogschool.blogs.repository;

import com.blogschool.blogs.entity.BlogPostEntity;
import com.blogschool.blogs.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}