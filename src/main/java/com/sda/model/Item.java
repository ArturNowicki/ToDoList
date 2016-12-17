package com.sda.model;

import com.sda.enums.ItemType;

public class Item {
	private int id;
	private String title;
	private String body;
	private ItemType type;
	int priority;
	int severity;
	User assignedTo;

	public Item() {}
	
	public Item(String title, String body, ItemType type, int priority, int severity, User assignedTo) {
		super();
		this.title = title;
		this.body = body;
		this.type = type;
		this.priority = priority;
		this.severity = severity;
		this.assignedTo = assignedTo;
	}
	
	
}
