package com.farhanhp.crud.article.api.models.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetArticleEditHistoriesRequest {

  private String articleWebId;
  private PaginationRequest paginationRequest;
}
