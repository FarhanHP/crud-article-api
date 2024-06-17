package com.farhanhp.crud.article.api.repositories;

import com.farhanhp.crud.article.api.models.db.ArticleEditHistoryDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleEditHistoryRepository extends ReactiveMongoRepository<ArticleEditHistoryDocument, ObjectId> {

  Mono<Void> deleteByArticleId(ObjectId articleId);

  Flux<ArticleEditHistoryDocument> findByArticleId(ObjectId articleId, Pageable pageable);

  Mono<Long> countByArticleId(ObjectId articleId);
}
