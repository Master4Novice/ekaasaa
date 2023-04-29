package com.learning.dwivna.ekaasaa.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalaryVO {
    private Float basicPay;

    private Float specialAllowance;

    private Float houseRentAllowance;

    private Float otherAllowance;

    private List<DeductionVO> deductions;

    private String creditDate;
}
