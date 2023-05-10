package com.learning.dwivna.ekaasaa.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Mono;

import java.util.List;

@NoRepositoryBean
public interface RedisCrudRepository<S, ID> extends RedisRepository<S, ID> {

    Mono<S> findById(ID id);

    Mono<List<S>> findAll();

    Mono<S> save(S entity);

    Mono<S> update(ID id, S entity);

    Mono<String> deleteById(ID id);

}
