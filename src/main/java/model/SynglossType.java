package model;

public enum SynglossType {
    PARENT("PARENT"), DESCENDANT("DESCENDANT");

    private final String value;
    SynglossType(String value) { this.value = value; }
}
