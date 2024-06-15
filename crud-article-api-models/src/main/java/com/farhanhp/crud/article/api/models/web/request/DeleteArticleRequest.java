package com.farhanhp.crud.article.api.models.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteArticleRequest {

  private String articleWebId;
}
