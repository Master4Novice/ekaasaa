package com.learning.dwivna.ekaasaa.service.impl;

import com.learning.dwivna.ekaasaa.service.UserService;
import com.learning.dwivna.ekaasaa.data.User;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ReactiveValueOperations<String, User> redisValueUserOperations;

    @Override
    public Mono<User> getUser(String id) {
        return redisValueUserOperations.get(id);
    }

    @Override
    public Mono<String> putUser(User user) {
        user.setId(UUID.randomUUID().toString());
        Mono<Boolean> result = redisValueUserOperations.set(user.getId(), user);
        return Boolean.TRUE.equals(result.block()) ? Mono.just(String.format("User added with ID:{%s}",user.getId())): Mono.just("User addition failed");
    }

    @Override
    public Mono<User> updateUser(String id, User user) {
        Mono<User> userMono = redisValueUserOperations.get(id);
        User existUser = userMono.block();
        if(existUser != null){
            existUser.setFirstName(user.getFirstName());
            existUser.setMiddleName(user.getMiddleName());
            existUser.setLastName(user.getLastName());
            redisValueUserOperations.set(id,existUser);
            return Mono.just(existUser);
        }
        return Mono.empty();
    }

    @Override
    public Mono<String> deleteUser(String id) {
        Mono<Boolean> result = redisValueUserOperations.delete(id);
        return Boolean.TRUE.equals(result.block()) ? Mono.just(String.format("User deleted having ID:{%s}",id)): Mono.just("User deletion failed");
    }

    @Override
    public Publisher<String> userSub() {
        return null;
    }
}
