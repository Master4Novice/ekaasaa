package com.learning.dwivna.ekaasaa.vo;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    private String typeOf;

    private String accountName;

    private Float credit;

    private Float balance;

    private Float debit;

    private String date;

    private String operation;
}
