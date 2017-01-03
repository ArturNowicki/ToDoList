package com.sda.enums;

public enum State {
	NEW(0, "NEW"), ACTIVE(1, "ACTIVE"), RESOLVED(2, "RESOLVED"), CLOSED(3, "CLOSED");
	
	private int value;
	private String key;
	
	private State(int value, String key) {
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
