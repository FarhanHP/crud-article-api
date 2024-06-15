package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.CommandExecutor;
import com.farhanhp.crud.article.api.commands.EditArticleCommand;
import com.farhanhp.crud.article.api.commands.GetArticleDocumentByWebId;
import com.farhanhp.crud.article.api.helpers.UtilHelper;
import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.models.db.ArticleEditHistoryDocument;
import com.farhanhp.crud.article.api.models.web.request.EditArticleRequest;
import com.farhanhp.crud.article.api.repositories.ArticleEditHistoryRepository;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class EditArticleCommandImpl implements EditArticleCommand {

  private final ArticleRepository articleRepository;

  private final ArticleEditHistoryRepository articleEditHistoryRepository;

  private final UtilHelper utilHelper;

  private final CommandExecutor executor;

  @Override
  public Mono<Boolean> execute(EditArticleRequest request) {

    return executor.execute(GetArticleDocumentByWebId.class, request.getArticleWebId())
        .flatMap(article -> validateRequest(request, article))
        .flatMap(article -> editArticle(request, article));
  }

  private Mono<ArticleDocument> validateRequest(EditArticleRequest request, ArticleDocument articleDocument) {
    if(articleDocument.getTitle().equals(request.getTitle()) && articleDocument.getBody().equals(request.getBody())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return Mono.just(articleDocument);
  }

  private Mono<Boolean> editArticle(EditArticleRequest request, ArticleDocument articleDocument) {

    return Mono.zip(articleRepository.save(getUpdatedArticle(request, articleDocument)),
            articleEditHistoryRepository.insert(toArticleEditHistoryDocument(articleDocument)))
        .map(ignored -> Boolean.TRUE);
  }

  private ArticleEditHistoryDocument toArticleEditHistoryDocument(ArticleDocument articleDocument) {

    return ArticleEditHistoryDocument.builder()
        .id(new ObjectId())
        .articleId(articleDocument.getId())
        .title(articleDocument.getTitle())
        .body(articleDocument.getBody())
        .createdAt(articleDocument.getUpdatedAt())
        .build();
  }

  private ArticleDocument getUpdatedArticle(EditArticleRequest request, ArticleDocument articleDocument) {

    return ArticleDocument.builder()
        .id(articleDocument.getId())
        .articleWebId(articleDocument.getArticleWebId())
        .title(request.getTitle())
        .body(request.getBody())
        .updatedAt(utilHelper.getCurrentEpochSeconds())
        .createdAt(articleDocument.getCreatedAt())
        .build();
  }
}
