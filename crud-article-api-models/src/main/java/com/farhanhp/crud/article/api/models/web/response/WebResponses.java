package com.farhanhp.crud.article.api.models.web.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebResponses<T> {

  private Integer code;
  private String status;
  private List<T> data;
  private PaginationResponse pagination;
}
