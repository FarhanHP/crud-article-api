package com.farhanhp.crud.article.api.models.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetArticleResponse {

  private String articleWebId;

  private String title;

  private String body;

  private boolean isEdited;

  private long createdAt;
}
