package com.example.race.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Races {
    private Integer id;
    private String name;
    private Date startDate;
    private Date bettingDeadlineDate;
}
