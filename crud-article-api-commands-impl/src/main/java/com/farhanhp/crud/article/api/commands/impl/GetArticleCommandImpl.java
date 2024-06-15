package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.CommandExecutor;
import com.farhanhp.crud.article.api.commands.GetArticleCommand;
import com.farhanhp.crud.article.api.commands.GetArticleDocumentByWebId;
import com.farhanhp.crud.article.api.helpers.ResponseHelper;
import com.farhanhp.crud.article.api.models.web.response.GetArticleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class GetArticleCommandImpl implements GetArticleCommand {

  private final ResponseHelper responseHelper;

  private final CommandExecutor executor;

  @Override
  public Mono<GetArticleResponse> execute(String articleWebId) {
    return executor.execute(GetArticleDocumentByWebId.class, articleWebId)
        .map(responseHelper::toGetArticleResponse);
  }
}
