package com.farhanhp.crud.article.api.helpers;

import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import com.farhanhp.crud.article.api.models.web.response.WebResponse;
import com.farhanhp.crud.article.api.models.web.response.WebResponses;
import java.util.List;
import org.springframework.data.util.Pair;

public interface ResponseHelper {

  <T> WebResponse<T> createSuccessWebResponse(T responseBody);

  <T> WebResponses<T> createSuccessWebResponse(Pair<List<T>, PaginationResponse> responsePair);

  GetArticleResponse toGetArticleResponse(ArticleDocument articleDocument);

  PaginationResponse toPaginationResponse(Long collectionSize, PaginationRequest request);
}
