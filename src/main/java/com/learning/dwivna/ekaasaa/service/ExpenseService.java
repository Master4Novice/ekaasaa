package com.learning.dwivna.ekaasaa.service;

import com.learning.dwivna.ekaasaa.data.Expense;
import com.learning.dwivna.ekaasaa.data.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExpenseService {
    Mono<String> putUserExpenses(String userId, List<Expense> expenses);

    Mono<User> updateUserExpenses(String userId, List<Expense> expenses);

    Mono<String> deleteUserExpense(String id);
}
