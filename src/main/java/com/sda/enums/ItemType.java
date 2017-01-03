package com.sda.enums;

public enum ItemType {
	BUG(0, "BUG"), TASK(1, "TASK"), FEATURE(2,"FEATURE");
	private int value;
	private String key;
	
	ItemType(int value, String key) {
		this.value = value;
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}