package com.classhub.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Faculties")
public class Faculty extends User {

	private ArrayList<String> skills = new ArrayList<>();

	public void addSkills(String skill) {
		skills.add(skill);
	}
}
