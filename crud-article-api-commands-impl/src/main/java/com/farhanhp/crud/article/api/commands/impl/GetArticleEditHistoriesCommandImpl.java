package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.CommandExecutor;
import com.farhanhp.crud.article.api.commands.GetArticleDocumentByWebId;
import com.farhanhp.crud.article.api.commands.GetArticleEditHistoriesCommand;
import com.farhanhp.crud.article.api.helpers.RequestHelper;
import com.farhanhp.crud.article.api.helpers.ResponseHelper;
import com.farhanhp.crud.article.api.models.db.ArticleEditHistoryDocument;
import com.farhanhp.crud.article.api.models.web.request.GetArticleEditHistoriesRequest;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.response.ArticleEditHistoryResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import com.farhanhp.crud.article.api.repositories.ArticleEditHistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@AllArgsConstructor
public class GetArticleEditHistoriesCommandImpl implements GetArticleEditHistoriesCommand {

  private final CommandExecutor executor;

  private final ResponseHelper responseHelper;

  private final RequestHelper requestHelper;

  private final ArticleEditHistoryRepository articleEditHistoryRepository;

  @Override
  public Mono<Pair<List<ArticleEditHistoryResponse>, PaginationResponse>> execute(
      GetArticleEditHistoriesRequest request) {

    return executor.execute(GetArticleDocumentByWebId.class, request.getArticleWebId())
        .flatMap(articleDocument -> getHistories(articleDocument.getId(), request.getPaginationRequest()))
        .zipWith(articleEditHistoryRepository.count())
        .map(tuple -> toPairResponse(request.getPaginationRequest(), tuple));
  }

  private Mono<List<ArticleEditHistoryResponse>> getHistories(ObjectId articleId, PaginationRequest paginationRequest) {

    return articleEditHistoryRepository.findByArticleId(articleId, requestHelper.createSortedByCreatedAtDescendingPageRequest(paginationRequest))
        .map(this::toArticleEditHistoryResponse)
        .collectList();
  }

  private ArticleEditHistoryResponse toArticleEditHistoryResponse(ArticleEditHistoryDocument articleEditHistoryDocument) {

    return ArticleEditHistoryResponse.builder()
        .title(articleEditHistoryDocument.getTitle())
        .body(articleEditHistoryDocument.getBody())
        .createdAt(articleEditHistoryDocument.getCreatedAt())
        .build();
  }

  private Pair<List<ArticleEditHistoryResponse>, PaginationResponse> toPairResponse(
      PaginationRequest request, Tuple2<List<ArticleEditHistoryResponse>, Long> responseTuple) {

    return Pair.of(responseTuple.getT1(),
        responseHelper.toPaginationResponse(responseTuple.getT2(), request));
  }
}
