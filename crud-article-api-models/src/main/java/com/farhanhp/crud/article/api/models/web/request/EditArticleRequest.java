package com.farhanhp.crud.article.api.models.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditArticleRequest {

  private String articleWebId;

  private String title;

  private String body;
}
