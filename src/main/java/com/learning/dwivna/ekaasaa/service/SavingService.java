package com.learning.dwivna.ekaasaa.service;

import com.learning.dwivna.ekaasaa.data.Saving;
import com.learning.dwivna.ekaasaa.data.User;
import reactor.core.publisher.Mono;

public interface SavingService {

    Mono<String> putUserSavings(String userId, Saving savings);

    Mono<User> updateUserSavings(String userId, Saving savings);

    Mono<String> deleteUserSaving(String id);
}
