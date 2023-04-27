package com.learning.dwivna.ekaasaa.service.impl;

import com.learning.dwivna.ekaasaa.service.UserService;
import com.learning.dwivna.ekaasaa.vo.UserVO;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Mono<UserVO> getUser(String id) {
        return null;
    }

    @Override
    public Mono<String> putUser(UserVO user) {
        return null;
    }

    @Override
    public Mono<UserVO> updateUser(String id, UserVO user) {
        return null;
    }

    @Override
    public Mono<String> deleteUser(String id) {
        return null;
    }

    @Override
    public Publisher<String> userSub() {
        return null;
    }
}
