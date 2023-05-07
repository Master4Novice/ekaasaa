package com.learning.dwivna.ekaasaa.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user-expense")
public class Expense {

    @Id
    private String id;

    private String userId;

    private String typeOf;

    private String dateOf;

    private String category;

    private String expenseFor;

    private Float amount;
}
