package com.farhanhp.crud.article.api.models.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertArticleRequest {

  @NotBlank
  @Size(min = 1, max = 20)
  private String title;

  @NotBlank
  @Size(min = 1, max = 500)
  private String body;
}
