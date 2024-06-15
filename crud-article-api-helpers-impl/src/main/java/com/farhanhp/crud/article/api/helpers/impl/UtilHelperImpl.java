package com.farhanhp.crud.article.api.helpers.impl;

import com.farhanhp.crud.article.api.helpers.UtilHelper;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class UtilHelperImpl implements UtilHelper {

  @Override
  public long getCurrentEpochSeconds() {

    return Instant.now().getEpochSecond();
  }
}
