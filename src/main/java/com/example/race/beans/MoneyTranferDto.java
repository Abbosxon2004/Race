package com.example.race.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTranferDto {
    private Integer user_id;
    private Integer race_id;
    private Integer horse_id;
    private Integer result_position;
    private String bet_position;
    private Integer betting_money;
}
