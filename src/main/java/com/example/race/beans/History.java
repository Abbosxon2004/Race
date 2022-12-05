package com.example.race.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private String user;
    private String race;
    private Integer winMoney;
}
