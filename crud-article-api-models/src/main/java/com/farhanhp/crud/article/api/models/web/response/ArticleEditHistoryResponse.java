package com.farhanhp.crud.article.api.models.web.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleEditHistoryResponse {

  private String title;
  private String body;
  private long createdAt;
}
