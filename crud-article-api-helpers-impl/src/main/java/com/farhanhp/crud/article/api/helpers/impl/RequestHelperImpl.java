package com.farhanhp.crud.article.api.helpers.impl;

import com.farhanhp.crud.article.api.helpers.RequestHelper;
import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@Component
public class RequestHelperImpl implements RequestHelper {

  private static final String CREATED_AT = "createdAt";

  @Override
  public PageRequest createSortedByCreatedAtDescendingPageRequest(PaginationRequest request) {

    return PageRequest.of(request.getPageNumber(), request.getPageSize(),
        Sort.by(Direction.DESC, CREATED_AT));
  }
}
