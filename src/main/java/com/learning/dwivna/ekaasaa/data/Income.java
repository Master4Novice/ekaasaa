package com.learning.dwivna.ekaasaa.data;

import com.learning.dwivna.ekaasaa.vo.SalaryVO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash("user-income")
public class Income {

    @Id
    private String id;

    private String userId;

    private List<SalaryVO> salaries;

}
