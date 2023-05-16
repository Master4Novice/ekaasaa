package com.learning.dwivna.ekaasaa.repositories;

import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.exceptions.UserAddFailedException;
import com.learning.dwivna.ekaasaa.exceptions.UserNotDeletedException;
import com.learning.dwivna.ekaasaa.exceptions.UserNotFoundException;
import com.learning.dwivna.ekaasaa.exceptions.UserUpdateFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements RedisCrudRepository<User, String> {


    private final ReactiveRedisTemplate<String, User> reactiveMasterRedisTemplate;

    private final ReactiveValueOperations<String, User> reactiveValueOperations;

    @Autowired
    public UserRepository(@Qualifier("reactiveUserRedisTemplate") ReactiveRedisTemplate<String, User> reactiveMasterRedisTemplate, @Qualifier("reactiveUserValueOperations") ReactiveValueOperations<String, User> reactiveValueOperations) {
        this.reactiveMasterRedisTemplate = reactiveMasterRedisTemplate;
        this.reactiveValueOperations = reactiveValueOperations;
    }

    @Override
    public Mono<User> findById(String id) {
        return this.reactiveValueOperations.get(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    @Override
    public Mono<List<User>> findAll() {
        return this.reactiveMasterRedisTemplate.keys("*").collectList()
                .flatMap(keyList -> reactiveMasterRedisTemplate.opsForValue()
                        .multiGet(keyList))
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    @Override
    public Mono<User> save(User entity) {
        entity.setId(UUID.randomUUID().toString());
        return this.reactiveValueOperations.set(entity.getId(), entity)
                .flatMap(res -> res ? Mono.just(entity) : Mono.error(new UserAddFailedException()));
    }

    /**
     * This method need more refinement.
     * */
    @Override
    public Mono<User> update(String id, User entity) {
        User existingUser = this.findById(id).block();
        assert existingUser != null;
        existingUser.setFirstName(entity.getFirstName());
        existingUser.setMiddleName(entity.getMiddleName());
        existingUser.setLastName(entity.getLastName());
        return this.reactiveValueOperations.set(id, existingUser)
                .flatMap(res -> res ? Mono.just(existingUser) : Mono.error(new UserUpdateFailedException()));
    }

    @Override
    public Mono<String> deleteById(String id) {
        return this.reactiveValueOperations.delete(id)
                .flatMap(res -> res ? Mono.just(String.format("User deleted having ID:{%s}", id)) : Mono.error(new UserNotDeletedException()));
    }
}
