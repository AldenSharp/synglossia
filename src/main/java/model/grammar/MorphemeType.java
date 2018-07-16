package model.grammar;

public enum MorphemeType {
    ROOT("ROOT"), PREFIX("PREFIX"), SUFFIX("SUFFIX"), INFIX("INFIX"), ENDING("ENDING"),
    CONSONANT_SCHEME("CONSONANT_SCHEME"), VOWEL_SCHEME("VOWEL_SCHEME");

    private final String value;
    MorphemeType(String value) { this.value = value; }
}
