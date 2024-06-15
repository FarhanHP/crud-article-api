package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.GetArticlesCommand;
import com.farhanhp.crud.article.api.helpers.RequestHelper;
import com.farhanhp.crud.article.api.helpers.ResponseHelper;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@AllArgsConstructor
public class GetArticlesCommandImpl implements GetArticlesCommand {

  private final ArticleRepository articleRepository;

  private final ResponseHelper responseHelper;

  private final RequestHelper requestHelper;

  private static final String CREATED_AT = "createdAt";

  @Override
  public Mono<Pair<List<GetArticleResponse>, PaginationResponse>> execute(
      PaginationRequest request) {

    return Mono.zip(getArticles(request), articleRepository.count())
        .map(tupleResponse -> toPairResponse(request, tupleResponse));
  }

  private Mono<List<GetArticleResponse>> getArticles(PaginationRequest request) {

    return articleRepository.findByIdNotNull(
            requestHelper.createSortedByCreatedAtDescendingPageRequest(request))
        .map(responseHelper::toGetArticleResponse)
        .collectList();
  }

  private Pair<List<GetArticleResponse>, PaginationResponse> toPairResponse(
      PaginationRequest request, Tuple2<List<GetArticleResponse>, Long> responseTuple) {

    return Pair.of(responseTuple.getT1(),
        responseHelper.toPaginationResponse(responseTuple.getT2(), request));
  }
}
