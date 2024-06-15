package com.farhanhp.crud.article.api.commands;

import reactor.core.publisher.Mono;

public interface Command<T, U> {

  Mono<U> execute(T commandRequest);
}
