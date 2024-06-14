package com.farhanhp.crud.article.api.repositories;

import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ArticleRepository extends ReactiveMongoRepository<ArticleDocument, ObjectId> {
}
