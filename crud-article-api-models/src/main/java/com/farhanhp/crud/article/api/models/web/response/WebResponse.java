package com.farhanhp.crud.article.api.models.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WebResponse<T> {
  private Integer code;
  private String status;
  private T data;
}
