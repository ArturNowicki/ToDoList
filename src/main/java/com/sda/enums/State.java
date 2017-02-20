package com.sda.enums;

public enum State {
	NEW("NEW", 0), ACTIVE("ACTIVE", 1), RESOLVED("RESOLVED", 2), CLOSED("CLOSED", 3);
	
	private String state;
	private int value;

	private State(String state, int value) {
		this.state = state;
		this.value = value;
	}

	public String getState() {
		return state;
	}

	public int getValue() {
		return value;
	}	
	
}
