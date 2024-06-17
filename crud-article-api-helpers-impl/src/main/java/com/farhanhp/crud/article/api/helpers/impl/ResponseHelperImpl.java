package com.farhanhp.crud.article.api.helpers.impl;

import com.farhanhp.crud.article.api.helpers.ResponseHelper;
import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import com.farhanhp.crud.article.api.models.web.response.WebResponse;
import com.farhanhp.crud.article.api.models.web.response.WebResponses;
import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelperImpl implements ResponseHelper {

  @Override
  public <T> WebResponse<T> createSuccessWebResponse(T responseBody) {
    return WebResponse.<T>builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.getReasonPhrase())
        .data(responseBody)
        .build();
  }

  @Override
  public <T> WebResponses<T> createSuccessWebResponse(
      Pair<List<T>, PaginationResponse> responsePair) {
    return WebResponses.<T>builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.getReasonPhrase())
        .data(responsePair.getFirst())
        .pagination(responsePair.getSecond())
        .build();
  }

  @Override
  public GetArticleResponse toGetArticleResponse(ArticleDocument articleDocument) {

    return GetArticleResponse.builder()
        .articleWebId(articleDocument.getArticleWebId())
        .title(articleDocument.getTitle())
        .body(articleDocument.getBody())
        .isEdited(articleDocument.getCreatedAt() != articleDocument.getUpdatedAt())
        .createdAt(articleDocument.getCreatedAt())
        .build();
  }

  @Override
  public PaginationResponse toPaginationResponse(Long collectionSize, PaginationRequest request) {

    return PaginationResponse.builder()
        .pageNumber(request.getPageNumber())
        .pageSize(request.getPageSize())
        .hasNextPage(isHasNextPage(collectionSize, request))
        .build();
  }

  private boolean isHasNextPage(Long collectionSize, PaginationRequest request) {

    return collectionSize > (long) (request.getPageNumber() + 1) * request.getPageSize();
  }
}
