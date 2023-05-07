package com.learning.dwivna.ekaasaa.service;

import com.learning.dwivna.ekaasaa.data.Income;
import com.learning.dwivna.ekaasaa.data.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IncomeService {

    Mono<String> putUserIncomes(String userId, List<Income> incomes);

    Mono<User> updateUserIncomes(String userId, List<Income> incomes);

    Mono<String> deleteUserIncome(String id);
}
