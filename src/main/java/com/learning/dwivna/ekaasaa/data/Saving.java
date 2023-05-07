package com.learning.dwivna.ekaasaa.data;

import com.learning.dwivna.ekaasaa.vo.AccountVO;
import com.learning.dwivna.ekaasaa.vo.DeductionVO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user-savings")
public class Saving {

    @Id
    private String id;

    private String userId;

    private List<AccountVO> accounts;

    private List<DeductionVO> deductions;
}
