package com.farhanhp.crud.article.api.models.db;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "article_edit_history")
@Data
@Builder
public class ArticleEditHistoryDocument {

  @Id
  private ObjectId id;

  private ObjectId articleId;

  private String title;

  private String body;

  private long createdAt;
}
