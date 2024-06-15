package com.farhanhp.crud.article.api.repositories;

import com.farhanhp.crud.article.api.models.db.ArticleDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository extends ReactiveMongoRepository<ArticleDocument, ObjectId> {

  Mono<ArticleDocument> findByArticleWebId(String articleWebId);

  Flux<ArticleDocument> findByIdNotNull(Pageable pageable);
}
