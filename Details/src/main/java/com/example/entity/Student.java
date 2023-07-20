package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Entity class create table
@AllArgsConstructor // all parametric constructor
@NoArgsConstructor // default constructor
@Data// create getter, setter, and toString
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // create unique key and auto numbering
	private Integer roll;
	private String name;
	private Integer age;
	private String email;
	private double marks;
	
}
