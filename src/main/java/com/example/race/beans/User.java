package com.example.race.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String fullName;
	private String username;
	private String password;
	private String role;
	private Integer balance;
}
