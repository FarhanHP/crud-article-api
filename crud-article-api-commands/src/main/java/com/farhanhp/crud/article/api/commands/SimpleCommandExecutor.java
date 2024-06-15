package com.farhanhp.crud.article.api.commands;

import reactor.core.publisher.Mono;

public interface SimpleCommandExecutor {

  <U, V extends SimpleCommand<U>> Mono<U> execute(Class<V> commandClassName);
}
