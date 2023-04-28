package com.learning.dwivna.ekaasaa.service;

import com.learning.dwivna.ekaasaa.data.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUser(String id);

    Mono<String> putUser(User user);

    Mono<User> updateUser(String id, User user);

    Mono<String> deleteUser(String id);

    Publisher<String> userSub();
}
