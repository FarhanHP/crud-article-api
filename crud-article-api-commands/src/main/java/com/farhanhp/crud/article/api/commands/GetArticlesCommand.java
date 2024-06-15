package com.farhanhp.crud.article.api.commands;

import com.farhanhp.crud.article.api.models.web.request.PaginationRequest;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import java.util.List;
import org.springframework.data.util.Pair;

public interface GetArticlesCommand extends
    Command<PaginationRequest, Pair<List<GetArticleResponse>, PaginationResponse>> {

}
