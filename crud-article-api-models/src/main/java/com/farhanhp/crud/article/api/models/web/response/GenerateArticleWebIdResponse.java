package com.farhanhp.crud.article.api.models.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateArticleWebIdResponse {

  private String articleWebId;
}
