package com.farhanhp.crud.article.api.models.web.request;

import lombok.Data;

@Data
public class EditArticleRequest {

  private String title;

  private String body;
}
