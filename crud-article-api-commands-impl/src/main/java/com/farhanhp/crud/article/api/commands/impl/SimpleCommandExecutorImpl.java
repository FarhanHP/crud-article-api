package com.farhanhp.crud.article.api.commands.impl;

import com.farhanhp.crud.article.api.commands.SimpleCommand;
import com.farhanhp.crud.article.api.commands.SimpleCommandExecutor;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class SimpleCommandExecutorImpl implements SimpleCommandExecutor {

  private final ApplicationContext applicationContext;

  @Override
  public <U, V extends SimpleCommand<U>> Mono<U> execute(Class<V> commandClassName) {
    SimpleCommand<U> command = applicationContext.getBean(commandClassName);
    return command.execute();
  }
}
