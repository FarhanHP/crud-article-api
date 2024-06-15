package com.farhanhp.crud.article.api.commands;

import com.farhanhp.crud.article.api.models.web.request.UpsertArticleRequest;
import com.farhanhp.crud.article.api.models.web.response.CreateArticleResponse;

public interface CreateArticleCommand extends Command<UpsertArticleRequest, CreateArticleResponse> {
}
