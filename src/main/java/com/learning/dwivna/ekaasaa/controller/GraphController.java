package com.learning.dwivna.ekaasaa.controller;

import com.learning.dwivna.ekaasaa.data.Expense;
import com.learning.dwivna.ekaasaa.data.Income;
import com.learning.dwivna.ekaasaa.data.Saving;
import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.service.ExpenseService;
import com.learning.dwivna.ekaasaa.service.IncomeService;
import com.learning.dwivna.ekaasaa.service.SavingService;
import com.learning.dwivna.ekaasaa.service.UserService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class GraphController {

    private final UserService userService;

    private final IncomeService incomeService;

    private final SavingService savingService;

    private final ExpenseService expenseService;

    @Autowired
    public GraphController(UserService userService, IncomeService incomeService, SavingService savingService, ExpenseService expenseService) {
        this.userService = userService;
        this.incomeService = incomeService;
        this.savingService = savingService;
        this.expenseService = expenseService;
    }

    @QueryMapping
    public Mono<User> getUser(@Argument String id) {
        return this.userService.getUser(id);
    }

    @QueryMapping
    public Mono<List<User>> getUsers() {
        return this.userService.getUsers();
    }

    @MutationMapping
    public Mono<User> putUser(@Argument User user) {
        return this.userService.putUser(user);
    }

    @MutationMapping
    public Mono<User> updateUser(@Argument String id, @Argument User user) {
        return this.userService.updateUser(id, user);
    }

    @MutationMapping
    public Mono<String> deleteUser(@Argument String id) {
        return this.userService.deleteUser(id);
    }

    @MutationMapping
    public Mono<String> putUserIncomes(@Argument String userId, @Argument List<Income> incomes) {
        return this.incomeService.putUserIncomes(userId, incomes);
    }

    @MutationMapping
    public Mono<User> updateUserIncomes(@Argument String userId, @Argument List<Income> incomes) {
        return this.incomeService.updateUserIncomes(userId, incomes);
    }

    @MutationMapping
    public Mono<String> deleteUserIncome(@Argument String id) {
        return this.incomeService.deleteUserIncome(id);
    }

    @MutationMapping
    public Mono<String> putUserSavings(@Argument String userId, @Argument Saving savings) {
        return this.savingService.putUserSavings(userId, savings);
    }

    @MutationMapping
    public Mono<User> updateUserSavings(@Argument String userId, @Argument Saving savings) {
        return this.savingService.updateUserSavings(userId, savings);
    }

    @MutationMapping
    public Mono<String> deleteUserSaving(@Argument String id) {
        return this.savingService.deleteUserSaving(id);
    }

    @MutationMapping
    public Mono<String> putUserExpenses(@Argument String userId, @Argument List<Expense> expenses) {
        return this.expenseService.putUserExpenses(userId, expenses);
    }

    @MutationMapping
    public Mono<User> updateUserExpenses(@Argument String userId, @Argument List<Expense> expenses) {
        return this.expenseService.updateUserExpenses(userId, expenses);
    }

    @MutationMapping
    public Mono<String> deleteUserExpense(@Argument String id) {
        return this.expenseService.deleteUserExpense(id);
    }

    @SubscriptionMapping
    public Publisher<List<User>> userSub() {
        return this.userService.userSub();
    }
}
