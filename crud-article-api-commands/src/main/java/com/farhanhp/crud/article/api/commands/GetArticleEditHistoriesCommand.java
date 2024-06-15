package com.farhanhp.crud.article.api.commands;

import com.farhanhp.crud.article.api.models.web.request.GetArticleEditHistoriesRequest;
import com.farhanhp.crud.article.api.models.web.response.ArticleEditHistoryResponse;
import com.farhanhp.crud.article.api.models.web.response.PaginationResponse;
import java.util.List;
import org.springframework.data.util.Pair;

public interface GetArticleEditHistoriesCommand extends
    Command<GetArticleEditHistoriesRequest, Pair<List<ArticleEditHistoryResponse>, PaginationResponse>> {

}
