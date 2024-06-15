package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.CommandExecutor;
import com.farhanhp.crud.article.api.commands.DeleteArticleCommand;
import com.farhanhp.crud.article.api.commands.GetArticleDocumentByWebId;
import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.models.web.request.DeleteArticleRequest;
import com.farhanhp.crud.article.api.repositories.ArticleEditHistoryRepository;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class DeleteArticleCommandImpl implements DeleteArticleCommand {

  private final ArticleRepository articleRepository;

  private final ArticleEditHistoryRepository articleEditHistoryRepository;

  private final CommandExecutor executor;

  @Override
  public Mono<Boolean> execute(DeleteArticleRequest commandRequest) {

    return executor.execute(GetArticleDocumentByWebId.class, commandRequest.getArticleWebId())
        .flatMap(this::deleteArticleAndRelatedDocuments);
  }

  private Mono<Boolean> deleteArticleAndRelatedDocuments(ArticleDocument article) {
    return Mono.zip(articleRepository.deleteById(article.getId()), articleEditHistoryRepository.deleteByArticleId(article.getId()))
        .map(ignored -> Boolean.TRUE);
  }
}
