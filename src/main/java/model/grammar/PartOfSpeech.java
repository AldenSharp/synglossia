package model.grammar;

public enum PartOfSpeech {
    NOUN("NOUN"), VERB("VERB");

    private final String value;
    PartOfSpeech(String value) { this.value = value; }
}
