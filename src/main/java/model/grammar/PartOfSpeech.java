package model.grammar;

public enum PartOfSpeech {
    NOUN("NOUN"), VERB("VERB"), VERB_INFINITIVE("VERB_INFINITIVE"), ADJECTIVE("ADJECTIVE"), ADVERB("ADVERB"), PRONOUN("PRONOUN");

    private final String value;
    PartOfSpeech(String value) { this.value = value; }
}
