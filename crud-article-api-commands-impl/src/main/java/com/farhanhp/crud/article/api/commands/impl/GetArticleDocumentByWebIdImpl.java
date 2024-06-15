package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.GetArticleDocumentByWebId;
import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class GetArticleDocumentByWebIdImpl implements GetArticleDocumentByWebId {

  private final ArticleRepository articleRepository;

  @Override
  public Mono<ArticleDocument> execute(String articleWebId) {

    return articleRepository.findByArticleWebId(articleWebId)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }
}
