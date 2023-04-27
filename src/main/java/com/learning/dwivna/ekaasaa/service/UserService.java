package com.learning.dwivna.ekaasaa.service;

import com.learning.dwivna.ekaasaa.vo.UserVO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserVO> getUser(String id);

    Mono<String> putUser(UserVO user);

    Mono<UserVO> updateUser(String id, UserVO user);

    Mono<String> deleteUser(String id);

    Publisher<String> userSub();
}
