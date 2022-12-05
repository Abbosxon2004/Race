package com.example.race.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceResults {
    private String race_name;
    private String horse_name;
    private Integer position;
}
