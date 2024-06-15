package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.CreateArticleCommand;
import com.farhanhp.crud.article.api.commands.GenerateArticleWebIdSimpleCommand;
import com.farhanhp.crud.article.api.commands.SimpleCommandExecutor;
import com.farhanhp.crud.article.api.helpers.UtilHelper;
import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.models.web.request.UpsertArticleRequest;
import com.farhanhp.crud.article.api.models.web.response.CreateArticleResponse;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CreateArticleCommandImpl implements CreateArticleCommand {

  private final ArticleRepository articleRepository;

  private final SimpleCommandExecutor simpleExecutor;

  private final UtilHelper utilHelper;

  @Override
  public Mono<CreateArticleResponse> execute(UpsertArticleRequest commandRequest) {

    return simpleExecutor.execute(GenerateArticleWebIdSimpleCommand.class)
        .flatMap(response -> insertArticle(response.getArticleWebId(), commandRequest))
        .map(this::toCreateArticleResponse);
  }

  private Mono<String> insertArticle(String webId, UpsertArticleRequest commandRequest) {
    return articleRepository.insert(ArticleDocument.builder()
            .id(new ObjectId())
            .articleWebId(webId)
            .title(commandRequest.getTitle())
            .body(commandRequest.getBody())
            .updatedAt(utilHelper.getCurrentEpochSeconds())
            .createdAt(utilHelper.getCurrentEpochSeconds())
            .build())
        .map(ArticleDocument::getArticleWebId);
  }

  private CreateArticleResponse toCreateArticleResponse(String articleWebId) {
    return CreateArticleResponse.builder()
        .articleWebId(articleWebId)
        .build();
  }
}
