package com.learning.dwivna.ekaasaa.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash("user")
public class User {

    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
}
