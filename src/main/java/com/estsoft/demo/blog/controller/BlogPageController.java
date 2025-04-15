package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.ArticleViewResponse;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {

    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articleList = blogService.findArticles()
                .stream().map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", articleList);
        return "articleList";
    }

    // /articles/{id}
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model){
        //게시글 단건 조회
        Article article = blogService.findArticle(id);

        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
    // /new-article -> newArtcile.html
    @GetMapping("/new-article")
    public String showBlogEdit(@RequestParam(required = false) Long id ,Model model){
        if(id == null){
            // 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else{
            // 수정
            Article article =blogService.findArticle(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";

    }

}
