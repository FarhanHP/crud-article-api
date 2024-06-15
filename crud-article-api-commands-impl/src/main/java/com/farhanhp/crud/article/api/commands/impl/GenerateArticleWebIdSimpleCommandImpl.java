package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.GenerateArticleWebIdSimpleCommand;
import com.farhanhp.crud.article.api.models.web.response.GenerateArticleWebIdResponse;
import com.farhanhp.crud.article.api.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GenerateArticleWebIdSimpleCommandImpl implements GenerateArticleWebIdSimpleCommand {

  private final ArticleRepository articleRepository;

  private static final int ID_LENGTH = 5;

  @Override
  public Mono<GenerateArticleWebIdResponse> execute() {

    return generateArticleWebId()
        .map(this::toGenerateArticleWebIdResponse);
  }

  private Mono<String> generateArticleWebId() {

    String articleWebId = generateRandomId();
    return articleRepository.findByArticleWebId(articleWebId)
        .flatMap(document -> generateArticleWebId())
        .defaultIfEmpty(articleWebId);
  }

  private String generateRandomId() {
    return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
  }

  private GenerateArticleWebIdResponse toGenerateArticleWebIdResponse(String webId) {
    return GenerateArticleWebIdResponse.builder()
        .articleWebId(webId)
        .build();
  }
}
