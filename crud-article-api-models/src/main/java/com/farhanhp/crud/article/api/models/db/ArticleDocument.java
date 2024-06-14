package com.farhanhp.crud.article.api.models.db;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ArticleDocument {

  @Id
  private ObjectId id;

  private String webId;

  private String title;

  private String body;

  private boolean edited;

  private long createdAt;
}
