package com.farhanhp.crud.article.api.helpers;

import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;

public interface RequestHelper {

  PageRequest createSortedByCreatedAtDescendingPageRequest(PaginationRequest request);
}
