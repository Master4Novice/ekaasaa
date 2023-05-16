package com.learning.dwivna.ekaasaa.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash("user")
public class User {

    @Id
    @Indexed
    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    private Set<Income> incomes;

    private Saving savings;

    private Set<Expense> expenses;
}
