package com.farhanhp.crud.article.api.repositories;

import com.farhanhp.crud.article.api.models.db.ArticleEditHistoryDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ArticleEditHistoryRepository extends ReactiveMongoRepository<ArticleEditHistoryDocument, ObjectId> {
}
