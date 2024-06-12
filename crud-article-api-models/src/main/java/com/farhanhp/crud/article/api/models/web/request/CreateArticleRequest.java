package com.farhanhp.crud.article.api.models.web.request;

import lombok.Data;

@Data
public class CreateArticleRequest {

  private String title;

  private String body;
}
