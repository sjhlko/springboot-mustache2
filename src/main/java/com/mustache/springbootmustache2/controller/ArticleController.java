package com.mustache.springbootmustache2.controller;

import com.mustache.springbootmustache2.ArticleRepository;
import com.mustache.springbootmustache2.domain.dto.ArticleDto;
import com.mustache.springbootmustache2.domain.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "layouts/list";
    }

    @GetMapping(value = "")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "layouts/show";
        } else {
            return "layouts/error";
        }
    }

    @PostMapping("/posts")
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.getTitle());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId:{}", savedArticle.getId());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
}