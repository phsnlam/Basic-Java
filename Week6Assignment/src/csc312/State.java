package csc312;

public enum State {
	NY ("New York"),
	VT ("Vermont");

	private final String name;

	State(String fullName) {
		name = fullName;
	}

	public String getName() {
		return name;
	}
	
	
}
