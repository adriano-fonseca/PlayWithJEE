package com.company.app.model;

public enum Side {
	LEFT("L", "Left"), RIGHT("R", "Right");
	
  private final String key;

  private final String value;
	
	Side(String key, String value) {
    this.key = key;
    this.value = value;
  }
	
	public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
