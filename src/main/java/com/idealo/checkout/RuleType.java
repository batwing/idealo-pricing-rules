package com.idealo.checkout;

public enum RuleType {
  ALL("all"),
  SINGLE("single"),
  SET("set");

  private final String value;

  RuleType(String value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }
}
