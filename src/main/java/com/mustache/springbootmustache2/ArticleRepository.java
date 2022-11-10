package com.mustache.springbootmustache2;

import com.mustache.springbootmustache2.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
