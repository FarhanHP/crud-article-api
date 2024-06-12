package com.farhanhp.crud.article.api.controllers;

import com.farhanhp.crud.article.api.models.web.request.CreateArticleRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import com.farhanhp.crud.article.api.models.web.response.WebResponse;

@RestController
@RequestMapping(value = "/api/article", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

  @PostMapping(value = "/create")
  public Mono<WebResponse<Object>> createArticle(@RequestBody CreateArticleRequest request) {

    return Mono.just(WebResponse.builder()
        .code(200)
        .status("OK")
        .build())
        .subscribeOn(Schedulers.parallel());
  }

  @GetMapping(value = "/get")
  public Mono<WebResponse<Object>> getArticles(
      @RequestParam int startPosition,
      @RequestParam int limit) {

    return Mono.just(WebResponse.builder()
        .code(200)
        .status("OK")
        .build())
        .subscribeOn(Schedulers.parallel());
  }

  @PutMapping(value = "/{articleId}/edit")
  public Mono<WebResponse<Object>> editArticle(
      @PathVariable String articleId,
      @RequestBody CreateArticleRequest request) {

    return Mono.just(WebResponse.builder()
        .code(200)
        .status("OK")
        .build())
        .subscribeOn(Schedulers.parallel());
  }

  @DeleteMapping(value = "/{articleId}/delete")
  public Mono<WebResponse<Object>> deleteArticle(
      @PathVariable String articleId) {

    return Mono.just(WebResponse.builder()
        .code(200)
        .status("OK")
        .build())
        .subscribeOn(Schedulers.parallel());
  }
}
