package com.farhanhp.crud.article.api.controllers;

import com.farhanhp.crud.article.api.commands.CommandExecutor;
import com.farhanhp.crud.article.api.commands.CreateArticleCommand;
import com.farhanhp.crud.article.api.commands.DeleteArticleCommand;
import com.farhanhp.crud.article.api.commands.EditArticleCommand;
import com.farhanhp.crud.article.api.commands.GetArticleCommand;
import com.farhanhp.crud.article.api.commands.GetArticleEditHistoriesCommand;
import com.farhanhp.crud.article.api.commands.GetArticlesCommand;
import com.farhanhp.crud.article.api.helpers.ResponseHelper;
import com.farhanhp.crud.article.api.models.web.request.EditArticleRequest;
import com.farhanhp.crud.article.api.models.web.request.GetArticleEditHistoriesRequest;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.request.UpsertArticleRequest;
import com.farhanhp.crud.article.api.models.web.request.DeleteArticleRequest;
import com.farhanhp.crud.article.api.models.web.response.ArticleEditHistoryResponse;
import com.farhanhp.crud.article.api.models.web.response.CreateArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.WebResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@AllArgsConstructor
@CrossOrigin
public class ArticleController {

  private final CommandExecutor executor;

  private final ResponseHelper responseHelper;

  @PostMapping(value = "/create")
  public Mono<WebResponse<CreateArticleResponse>> createArticle(
      @RequestBody @Valid UpsertArticleRequest request) {

    return executor.execute(CreateArticleCommand.class, request)
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }

  @GetMapping(value = "/get")
  public Mono<WebResponses<GetArticleResponse>> getArticles(
      @RequestParam int pageNumber, @RequestParam int pageSize) {

    return executor.execute(GetArticlesCommand.class, PaginationRequest.builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .build())
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }

  @GetMapping(value = "/{articleWebId}/get")
  public Mono<WebResponse<GetArticleResponse>> getArticle(
      @PathVariable String articleWebId) {

    return executor.execute(GetArticleCommand.class, articleWebId)
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }

  @GetMapping(value = "/{articleWebId}/getEditHistories")
  public Mono<WebResponses<ArticleEditHistoryResponse>> getArticleEditHistories(
      @PathVariable String articleWebId, @RequestParam int pageNumber, @RequestParam int pageSize) {

    return executor.execute(GetArticleEditHistoriesCommand.class,
            GetArticleEditHistoriesRequest.builder()
                .articleWebId(articleWebId)
                .paginationRequest(PaginationRequest.builder()
                    .pageNumber(pageNumber)
                    .pageSize(pageSize)
                    .build())
                .build())
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }

  @PutMapping(value = "/{articleWebId}/edit")
  public Mono<WebResponse<Boolean>> editArticle(
      @PathVariable String articleWebId,
      @RequestBody @Valid UpsertArticleRequest request) {

    return executor.execute(EditArticleCommand.class, EditArticleRequest.builder()
            .articleWebId(articleWebId)
            .body(request.getBody())
            .title(request.getTitle())
            .build())
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }

  @DeleteMapping(value = "/{articleWebId}/delete")
  public Mono<WebResponse<Boolean>> deleteArticle(
      @PathVariable String articleWebId) {

    return executor.execute(DeleteArticleCommand.class, DeleteArticleRequest.builder()
            .articleWebId(articleWebId)
            .build())
        .map(responseHelper::createSuccessWebResponse)
        .subscribeOn(Schedulers.parallel());
  }
}
