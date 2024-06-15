package com.farhanhp.crud.article.api.models.db;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "article")
@Data
@Builder
public class ArticleDocument {

  @Id
  private ObjectId id;

  private String articleWebId;

  private String title;

  private String body;

  private long updatedAt;

  private long createdAt;
}
