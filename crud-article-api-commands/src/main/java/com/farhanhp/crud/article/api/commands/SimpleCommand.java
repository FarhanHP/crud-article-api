package com.farhanhp.crud.article.api.commands;

import reactor.core.publisher.Mono;

public interface SimpleCommand<T> {

  Mono<T> execute();
}
