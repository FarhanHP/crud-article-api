package com.farhanhp.crud.article.api.models.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationResponse {

  private int pageNumber;
  private int pageSize;
  private boolean hasNextPage;
}
