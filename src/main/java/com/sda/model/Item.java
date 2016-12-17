package com.sda.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.sda.enums.ItemType;
import com.sda.enums.State;

@Entity
public class Item {
	private int id;
	private String title;
	private String body;
	private ItemType type;
	private int priority;
	private int severity;
	private List<String> tags;
	private User assignedTo;
	private State state;
	private LocalDate created;
	private LocalDate modified;
	private int originalEstimate;
	private int remainingHours;
	private int completedHours;

}
