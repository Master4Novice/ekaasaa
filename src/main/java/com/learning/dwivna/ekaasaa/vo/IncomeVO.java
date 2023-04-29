package com.learning.dwivna.ekaasaa.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class IncomeVO {

    private List<SalaryVO> salaries;

}
