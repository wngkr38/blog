package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findArticles() {
        return blogRepository.findAll();
    }

    public Article findArticle(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not exists id: " + id));
    }

    public void deleteArticle(Long id) {
        blogRepository.deleteById(id); //delete from article where id = ${id}
    }

    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        //findById (수정하기 이전 Article 객체)
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exists id: " + id)); // 500 Error
        // update
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}